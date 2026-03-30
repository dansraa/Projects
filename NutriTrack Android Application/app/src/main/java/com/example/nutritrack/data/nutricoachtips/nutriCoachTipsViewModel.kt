package com.example.nutritrack.data.nutricoachtips

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

class NutriCoachTipsViewModel (private val applicationContext: Context) : ViewModel() {

    val db = NutriTrackDatabase.getDatabase(applicationContext)

    private val nutriCoachTipsRepository: nutriCoachTipsRepository =
        nutriCoachTipsRepository(applicationContext)


    // Insert tip into db.
    /**
     * @param nutriCoachTips
     */
    fun insertTip(nutriCoachTips: NutriCoachTips) {
        viewModelScope.launch(Dispatchers.IO) {
            nutriCoachTipsRepository.insertTip(nutriCoachTips)
        }
    }

    // Collects list of all tips based off user id within the entity.
    suspend fun getTipsByUserId(userId: Int): List<NutriCoachTips> {
        return nutriCoachTipsRepository.getTipsByUserId(userId)
    }

    // Viewmodel Factory
    class NutriCoachTipsViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            NutriCoachTipsViewModel(context) as T
    }
}