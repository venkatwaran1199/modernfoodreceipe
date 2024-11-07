package com.example.modernfoodreceipe.utils

class Constants {

    companion object{

        const val BASE_URL = "https://api.spoonacular.com"
        const val API_KEY = "3c16b010af8e4c3eb3a51a1e4ccf61f0"

        //APi Queries
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //Database
        const val DATABASE_NAME = "recipe_database"
        const val TABLE_NAME = "recipe_table"
    }
}