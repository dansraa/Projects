package com.example.nutritrack.data.nutricoachtips

import android.content.Context
import com.example.nutritrack.data.NutriTrackDatabase

class nutriCoachTipsRepository(context: Context) {

    private val nutriCoachTipsDao =
        NutriTrackDatabase.getDatabase(context).nutriCoachTipsDao()

    suspend fun insertTip(nutriCoachTips: NutriCoachTips) {
        nutriCoachTipsDao.insertTip(nutriCoachTips)
    }

    suspend fun getTipsByUserId(userId: Int): List<NutriCoachTips> {
        return nutriCoachTipsDao.getTipsByUserId(userId)
    }

}