package com.example.modernfoodreceipe.models.data


import com.google.gson.annotations.SerializedName

data class FoodReceipe(
    @SerializedName("results")
    val results: List<Result>
)