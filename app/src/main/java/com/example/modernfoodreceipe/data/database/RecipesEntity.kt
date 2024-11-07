package com.example.modernfoodreceipe.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.modernfoodreceipe.models.data.FoodReceipe
import com.example.modernfoodreceipe.utils.Constants.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class RecipesEntity(
    val foodReceipe: FoodReceipe
){

    @PrimaryKey(autoGenerate = false)
    var id:Int = 0
}

