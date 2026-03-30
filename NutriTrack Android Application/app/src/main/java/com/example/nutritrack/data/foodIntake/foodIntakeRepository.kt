package com.example.nutritrack.data.foodIntake

import android.content.Context
import com.example.nutritrack.data.NutriTrackDatabase

class FoodIntakeRepository(context: Context) {

    private val foodIntakeDao =
        NutriTrackDatabase.getDatabase(context).foodIntakeDao()

    suspend fun insertQuizAttempt(foodIntake: FoodIntake) {
        foodIntakeDao.insertResponse(foodIntake)
    }

}