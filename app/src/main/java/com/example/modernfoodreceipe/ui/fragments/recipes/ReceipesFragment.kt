package com.example.modernfoodreceipe.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.modernfoodreceipe.adapter.RecipeAdapter
import com.example.modernfoodreceipe.databinding.FragmentReceipesBinding
import com.example.modernfoodreceipe.utils.Constants.Companion.API_KEY
import com.example.modernfoodreceipe.utils.NetworkResult
import com.example.modernfoodreceipe.viewmodel.MainViewModel
import com.example.modernfoodreceipe.viewmodel.RecipeViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceipesFragment : Fragment() {

    private var _binding : FragmentReceipesBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainviewmodel:MainViewModel
    private lateinit var recipeviewmodel:RecipeViewmodel
    private val mAdapter by lazy { RecipeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainviewmodel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipeviewmodel = ViewModelProvider(requireActivity()).get(RecipeViewmodel::class.java)

    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
            _binding = FragmentReceipesBinding.inflate(inflater, container, false)

            setuprecyclerview()
            requestapidata()
            return binding.root
    }

    private fun setuprecyclerview() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showshimmereffect()
    }

    private fun requestapidata() {
        mainviewmodel.getRecipe(recipeviewmodel.applyqueries())
        mainviewmodel.recipeResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideshimmereffect()
                    response.data?.let { mAdapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    hideshimmereffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    showshimmereffect()
                }
            }
        }
    }


    private fun showshimmereffect(){
        binding.recyclerview.showShimmerAdapter()
    }

    private fun hideshimmereffect(){
        binding.recyclerview.hideShimmerAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}