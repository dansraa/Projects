package com.example.nutritrack.data.foodIntake

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nutritrack.data.NutriTrackDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @param context
 */

class FoodIntakeViewModel(private val applicationContext: Context) : ViewModel() {

    val db = NutriTrackDatabase.getDatabase(applicationContext)

    private val foodIntakeRepository: FoodIntakeRepository =
        FoodIntakeRepository(applicationContext)

    /**
     * @param foodIntake
     */
    fun insertResponse(foodIntake: FoodIntake) {
        viewModelScope.launch(Dispatchers.IO) {
            foodIntakeRepository.insertQuizAttempt(foodIntake)
        }
    }

    class FoodIntakeViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            FoodIntakeViewModel(context) as T
    }

}