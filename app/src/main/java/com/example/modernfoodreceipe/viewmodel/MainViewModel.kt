package com.example.modernfoodreceipe.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil3.util.CoilUtils
import com.example.modernfoodreceipe.data.Repository
import com.example.modernfoodreceipe.models.data.FoodReceipe
import com.example.modernfoodreceipe.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val recipeResponse: MutableLiveData<NetworkResult<FoodReceipe>> = MutableLiveData()

    fun getRecipe(queries: Map<String, String>) = viewModelScope.launch {
        getRecipeSafecall(queries)
    }

    private suspend fun getRecipeSafecall(queries: Map<String, String>) {
        recipeResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getReceipe(queries)
                recipeResponse.value = handleFoodrecipeResponce(response)

            } catch (e: Exception) {
                recipeResponse.value = NetworkResult.Error("Recipes not Found")
            }

        } else {
            recipeResponse.value = NetworkResult.Error("No Internet Connection")
        }

    }


    private fun handleFoodrecipeResponce(response: Response<FoodReceipe>): NetworkResult<FoodReceipe> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout.")
            }

            response.code() == 402 -> {
                return NetworkResult.Error("Api Key Limited")
            }


            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not Found")
            }

            response.isSuccessful -> {
                val foodReceipe = response.body()
                return NetworkResult.Success(foodReceipe!!)
            }

            else -> return NetworkResult.Error(response.message())
        }

    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

    }

}