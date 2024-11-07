package com.example.modernfoodreceipe.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertRecipe(recipesEntity: RecipesEntity)

    @Query("SELECT * FROM recipe_table ORDER BY id ASC ")
    fun readRecipe():Flow<List<RecipesEntity>>
}