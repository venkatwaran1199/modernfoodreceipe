package com.example.modernfoodreceipe.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [RecipesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipeDatabase:RoomDatabase() {

    abstract fun recipedao() : RecipeDao
}