package com.example.modernfoodreceipe.data

import com.example.modernfoodreceipe.data.network.FoodReceipeApi
import com.example.modernfoodreceipe.models.data.FoodReceipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodReceipeApi: FoodReceipeApi
) {

    suspend fun getReceipe(queries: Map<String , String>):Response<FoodReceipe>{
        return foodReceipeApi.getfoodreceipe(queries)
    }
}