package com.example.nutritrack.data.patient

import android.text.Selection
import androidx.annotation.Nullable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {


    // To insert a patient into the database.
    // Currently not in use.
    /**
     * @param patients
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(patients: List<Patient>)

    /**
     *@return
     */
    @Query("SELECT userId FROM patients")
    fun getPatientId():Flow<List<Int>>

    // Updating an existing row with an update query and adding patient name and password.
    @Query("UPDATE patients SET name = :name, password = :password WHERE userId = :userId and phoneNo = :phoneNo")
    suspend fun updatePatientAccount(userId: Int,phoneNo: String, name: String, password: String)

    /**
     * @return
     */
    // Validating an account against the patients table.
    @Query("SELECT COUNT(*) FROM patients WHERE userId = :userId AND password = :password")
    suspend fun validatePatientAccount(userId: Int, password: String): Int
    // Returns an integer of 1 or 0
    // 1 = Valid credentials against the database, authorised login.
    // 0 = Non-valid credentials against the database, no login auth.

    @Query ("SELECT name, phoneNo, userId FROM patients WHERE userId = :userId")
    suspend fun accountInfo(userId: Int): AccountInfo?

    // Grabbing the patients total score depending on the gender in the table.
    @Query(
        """SELECT 
        CASE 
            WHEN patientSex = 'Male' THEN heifaTotalScoreMale 
            WHEN patientSex = 'Female' THEN heifaTotalScoreFemale 
            ELSE NULL 
        END AS heifaTotalScore 
    FROM patients
    WHERE userId = :userId"""
    )
    suspend fun getPatientTotalScore(userId: Int): Double?

    @Query(
        "SELECT CASE WHEN patientSex = 'Male' THEN fruitHeifaScoreMale ELSE fruitHeifaScoreFemale END AS fruitHeifaScore FROM patients WHERE userId = :userId"
    )
    suspend fun getFruitScore(userId: Int): Double?


    // Collects all HEIFA scores dependant on the patients sex.
    @Query(
        """SELECT 
        CASE WHEN patientSex = 'Male' THEN heifaTotalScoreMale ELSE heifaTotalScoreFemale END AS heifaTotalScore,
        CASE WHEN patientSex = 'Male' THEN discretionaryHeifaScoreMale ELSE discretionaryHeifaScoreFemale END AS heifaDiscretionaryScore,
        CASE WHEN patientSex = 'Male' THEN vegeHeifaScoreMale ELSE vegeHeifaScoreFemale END AS vegeHeifaScore,
        CASE WHEN patientSex = 'Male' THEN fruitHeifaScoreMale ELSE fruitHeifaScoreFemale END AS fruitHeifaScore,
        CASE WHEN patientSex = 'Male' THEN grainCerealHeifaScoreMale ELSE grainCerealHeifaScoreFemale END AS grainCerealHeifaScore,
        CASE WHEN patientSex = 'Male' THEN wholeGrainHeifaScoreMale ELSE wholeGrainHeifaScoreFemale END AS wholeGrainHeifaScore,
        CASE WHEN patientSex = 'Male' THEN meatAltHeifaScoreMale ELSE meatAltHeifaScoreFemale END AS meatAltHeifaScore,
        CASE WHEN patientSex = 'Male' THEN dairyAltHeifaScoreMale ELSE dairyAltHeifaScoreFemale END AS dairyAltHeifaScore,
        CASE WHEN patientSex = 'Male' THEN sodiumHeifaScoreMale ELSE sodiumHeifaScoreFemale END AS sodiumHeifaScore,
        CASE WHEN patientSex = 'Male' THEN alcoholHeifaScoreMale ELSE alcoholHeifaScoreFemale END AS alcoholHeifaScore,
        CASE WHEN patientSex = 'Male' THEN waterHeifaScoreMale ELSE waterHeifaScoreFemale END AS waterHeifaScore,
        CASE WHEN patientSex = 'Male' THEN sugarHeifaScoreMale ELSE sugarHeifaScoreFemale END AS sugarHeifaScore,
        CASE WHEN patientSex = 'Male' THEN saturatedFatHeifaScoreMale ELSE saturatedFatHeifaScoreFemale END AS saturatedFatHeifaScore,
        CASE WHEN patientSex = 'Male' THEN unsaturatedFatHeifaScoreMale ELSE unsaturatedFatHeifaScoreFemale END AS unsaturatedFatHeifaScore
     FROM patients
     WHERE userId = :userId"""
    )
    suspend fun getAllPatientScores(userId: Int): PatientHeifaScores?

    /**
     *
     * Retrieve all patients and stats from the database as a flow of lists.
     * @return
     */

    // Select all patients and display data.
    // For debugging.
    @Query("SELECT * FROM patients")
    fun getAllPatients(): Flow<List<Patient>>


    //Finding the average total HEIFA scores.
    @Query("SELECT AVG(heifaTotalScoreMale) FROM patients")
    suspend fun getAverageMaleHeifa(): Double?

    @Query("SELECT AVG(heifaTotalScoreFemale) FROM patients")
    suspend fun getAverageFemaleHeifa(): Double?

}