package com.example.nutritrack.data.patient

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.nutritrack.data.NutriTrackDatabase
import kotlinx.coroutines.flow.Flow

class PatientRepository(context: Context) {

    private val patientDao =
        NutriTrackDatabase.getDatabase(context).patientDao()

    /**
     * @param patients
     * Inserts all data from CSV into DB.
     */
    suspend fun insertAll(patients: List<Patient>) {
        patientDao.insertAll(patients)
    }

    // Updates account credentials
    suspend fun updateAccount(userId: Int, phoneNo: String, name: String, password: String) {
        patientDao.updatePatientAccount(userId, phoneNo, name, password)
    }

    // Validates existing accounts against the database.
    suspend fun loginIsValid(userId: Int, password: String): Boolean {
        return patientDao.validatePatientAccount(userId, password) > 0
    }

    // Gets the patient total score from the patient table.
    suspend fun getPatientTotal(userId: Int): Double? {
        return patientDao.getPatientTotalScore(userId)
    }

    // Gets all the patient heifa scores from the patient table.
    suspend fun getAllPatientScores(userId: Int): PatientHeifaScores? {
        return patientDao.getAllPatientScores(userId)
    }

    suspend fun getAccountInfo(userId: Int): AccountInfo? {
        return patientDao.accountInfo(userId)
    }

    suspend fun getFruitScore(userId: Int): Double? {
        return patientDao.getFruitScore(userId)
    }

    /**
     * Retrieve all user IDs within the patient table.
     * @return
     */
    val allUserId: Flow<List<Int>> = patientDao.getPatientId()

    /**
     * Retrieve all patients within the database.
     * @return
     */
    val allPatient: Flow<List<Patient>> = patientDao.getAllPatients()

    suspend fun getAverageMaleHeifa(): Double? {
        return patientDao.getAverageMaleHeifa()
    }

    suspend fun getAverageFemaleHeifa(): Double? {
        return patientDao.getAverageFemaleHeifa()
    }


}