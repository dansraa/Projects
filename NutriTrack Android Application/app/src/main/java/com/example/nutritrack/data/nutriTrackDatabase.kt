package com.example.nutritrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nutritrack.data.foodIntake.FoodIntake
import com.example.nutritrack.data.foodIntake.FoodIntakeDao
import com.example.nutritrack.data.nutricoachtips.NutriCoachTips
import com.example.nutritrack.data.nutricoachtips.NutriCoachTipsDao
import com.example.nutritrack.data.patient.Patient
import com.example.nutritrack.data.patient.PatientDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database (entities = [Patient::class, FoodIntake::class, NutriCoachTips::class], version = 11, exportSchema = false)

abstract class NutriTrackDatabase : RoomDatabase() {

    /**
     * @return
     */
    // Providing access to the PatientDao interface for performing database operations.
    abstract fun patientDao(): PatientDao

    /**
     * @return
     */
    // Providing access to the FoodIntakeDao interface for performing database operations.
    abstract fun foodIntakeDao(): FoodIntakeDao

    /**
     * @return
     */
    // Providing access to the NutriCoachTipsDao interface for performing database operations.
    abstract fun nutriCoachTipsDao(): NutriCoachTipsDao


    companion object {
        @Volatile
        private var Instance: NutriTrackDatabase? = null

        /**
         *
         * @param context
         * @return
         */
        fun getDatabase(context: Context): NutriTrackDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NutriTrackDatabase::class.java, "item_database")
                    //.fallbackToDestructiveMigration() // Wipes database from schema
                    .build()
                    .also { Instance = it }
            }
        }
    }

}