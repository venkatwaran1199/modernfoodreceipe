package com.example.modernfoodreceipe.data.network

import com.example.modernfoodreceipe.models.data.FoodReceipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodReceipeApi {


    @GET("/recipes/complexSearch")
    suspend fun getfoodreceipe(
        @QueryMap queries : Map<String , String>
    ):Response<FoodReceipe>
}