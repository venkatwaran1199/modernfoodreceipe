package com.example.modernfoodreceipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.modernfoodreceipe.databinding.ReceipeRowLayoutBinding
import com.example.modernfoodreceipe.models.data.FoodReceipe
import com.example.modernfoodreceipe.models.data.Result
import com.example.modernfoodreceipe.utils.RecipeDiffUtil

class RecipeAdapter:RecyclerView.Adapter<RecipeAdapter.MyVIewHolder>() {

    var recipe = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVIewHolder {
        val binding = ReceipeRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return MyVIewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyVIewHolder, position: Int) {
        val curritem = recipe[position]
        holder.bind(curritem)
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

    class MyVIewHolder(private val binding:ReceipeRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(result:Result){
            binding.result = result
            binding.executePendingBindings()
        }
    }

    fun setData(newData:FoodReceipe){
        val recipediffutil = RecipeDiffUtil(recipe, newData.results)
        val recipediffresult = DiffUtil.calculateDiff(recipediffutil)
        recipe = newData.results
        recipediffresult.dispatchUpdatesTo(this)

    }
}