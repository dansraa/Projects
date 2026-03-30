package com.example.nutritrack.data.foodIntake

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface FoodIntakeDao {

    // Insert questionnaire attempts into food intake entity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponse(foodIntake: FoodIntake)

}