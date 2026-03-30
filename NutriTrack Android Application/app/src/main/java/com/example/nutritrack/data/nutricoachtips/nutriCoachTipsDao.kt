package com.example.nutritrack.data.nutricoachtips

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NutriCoachTipsDao {

    // Inserts the prompt into the database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTip(nutriCoachTips: NutriCoachTips)

    // Fetches a list of the saved prompts dependant on the userid.
    @Query("SELECT * FROM nutricoachtips WHERE tipUserId = :userId")
    suspend fun getTipsByUserId(userId: Int): List<NutriCoachTips>

}