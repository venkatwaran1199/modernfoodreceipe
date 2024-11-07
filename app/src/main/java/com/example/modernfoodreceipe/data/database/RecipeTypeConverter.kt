package com.example.modernfoodreceipe.data.database

import androidx.room.TypeConverter
import com.example.modernfoodreceipe.models.data.FoodReceipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun FoodRecipetoString(foodReceipe: FoodReceipe):String{
        return gson.toJson(foodReceipe)
    }

    fun stringtoFoodrecipe(data:String):FoodReceipe{
        val listtype = object :TypeToken<FoodReceipe>() {} .type
        return gson.fromJson(data, listtype)
    }

}