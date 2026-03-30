package com.example.nutritrack.data.patient

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nutritrack.data.NutriTrackDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import androidx.compose.runtime.State
import java.io.InputStreamReader

/**
 * @param context Application context.
 */

class PatientViewModel(private val applicationContext: Context) : ViewModel() {


    val db = NutriTrackDatabase.getDatabase(applicationContext)

    var totalScore by mutableStateOf<Double?>(null)
        private set

    var fruitScore by mutableStateOf<Double?>(null)
        private set

    // should not start with capital P fix this later.
    private val PatientRepository: PatientRepository =
        PatientRepository(applicationContext)

    private val _avgMaleScore = mutableStateOf<Double?>(null)
    val avgMaleScore: State<Double?> = _avgMaleScore

    private val _avgFemaleScore = mutableStateOf<Double?>(null)
    val avgFemaleScore: State<Double?> = _avgFemaleScore

    init {
        viewModelScope.launch {
            _avgMaleScore.value = PatientRepository.getAverageMaleHeifa()
            _avgFemaleScore.value = PatientRepository.getAverageFemaleHeifa()
        }
    }

    // Flow list of all user IDs within the patient table.
    val allUserId: Flow<List<Int>> = PatientRepository.allUserId;

    // flow of all patients within the database.
    val allPatient: Flow<List<Patient>> = PatientRepository.allPatient;

    // Update patient account details within the patient table.
    fun updateAccount(userId: Int, phoneNo: String, name: String, password: String) {
        viewModelScope.launch{
            PatientRepository.updateAccount(userId, phoneNo, name, password)
        }
    }

    // Validates existing patient account against the database.
    fun loginIsValid(userId: Int, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isValid = PatientRepository.loginIsValid(userId, password)
            onResult(isValid)
        }
    }

    // Gets patient total score from heifa data.
    fun getTotalScore(userId: Int) {
        viewModelScope.launch {
            totalScore = PatientRepository.getPatientTotal(userId)
        }
    }

    fun getPatientFruitScore(userId: Int) {
        viewModelScope.launch {
            fruitScore = PatientRepository.getFruitScore(userId)
        }
    }

    // Gets all patient heifa scores from heifa data.
    suspend fun getPatientHeifaScores(userId: Int): PatientHeifaScores? {
        return PatientRepository.getAllPatientScores(userId)
    }

    suspend fun getAccountInformation(userId: Int): AccountInfo? {
       return PatientRepository.getAccountInfo(userId)
    }

    class PatientViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            PatientViewModel(context) as T
    }

    // Reads through all the CSV file and inserts it into the patient table.
    fun readCSVAndInsert(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val inputStream = context.assets.open("data.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))

            val patients = mutableListOf<Patient>()

            reader.useLines {
                lines ->
                lines.drop(1).forEach { line ->
                    val tokens = line.split(",")

                    val patient = Patient(
                        phoneNo = tokens[0],
                        userId = tokens[1].toInt(),
                        patientSex = tokens[2],
                        heifaTotalScoreMale = tokens[3].toDoubleOrNull(),
                        heifaTotalScoreFemale = tokens[4].toDoubleOrNull(),
                        discretionaryHeifaScoreMale = tokens[5].toDoubleOrNull(),
                        discretionaryHeifaScoreFemale = tokens[6].toDoubleOrNull(),
                        discretionaryServeSize = tokens[7].toDoubleOrNull(),
                        vegeHeifaScoreMale = tokens[8].toDoubleOrNull(),
                        vegeHeifaScoreFemale = tokens[9].toDoubleOrNull(),
                        vegeLegumesServeSize = tokens[10].toDoubleOrNull(),
                        legumesAllocatedVege = tokens[11].toDoubleOrNull(),
                        vegeVariationsScore = tokens[12].toDoubleOrNull(),
                        vegeCruciferous = tokens[13].toDoubleOrNull(),
                        vegeTuberAndBulb = tokens[14].toDoubleOrNull(),
                        vegeOther = tokens[15].toDoubleOrNull(),
                        legumes = tokens[16].toDoubleOrNull(),
                        vegeGreen = tokens[17].toDoubleOrNull(),
                        vegeRedAndOrange = tokens[18].toDoubleOrNull(),
                        fruitHeifaScoreMale = tokens[19].toDoubleOrNull(),
                        fruitHeifaScoreFemale = tokens[20].toDoubleOrNull(),
                        fruitServeSize = tokens[21].toDoubleOrNull(),
                        fruitVariationsScore = tokens[22].toDoubleOrNull(),
                        fruitPome = tokens[23].toDoubleOrNull(),
                        fruitTropicalAndSubtropical = tokens[24].toDoubleOrNull(),
                        fruitBerry = tokens[25].toDoubleOrNull(),
                        fruitStone = tokens[26].toDoubleOrNull(),
                        fruitCitrus = tokens[27].toDoubleOrNull(),
                        fruitOther = tokens[28].toDoubleOrNull(),
                        grainCerealHeifaScoreMale = tokens[29].toDoubleOrNull(),
                        grainCerealHeifaScoreFemale = tokens[30].toDoubleOrNull(),
                        grainCerealServeSize = tokens[31].toDoubleOrNull(),
                        grainCerealNonWholeGrain = tokens[32].toDoubleOrNull(),
                        wholeGrainHeifaScoreMale = tokens[33].toDoubleOrNull(),
                        wholeGrainHeifaScoreFemale = tokens[34].toDoubleOrNull(),
                        wholeGrainServeSize = tokens[35].toDoubleOrNull(),
                        meatAltHeifaScoreMale = tokens[36].toDoubleOrNull(),
                        meatAltHeifaScoreFemale = tokens[37].toDoubleOrNull(),
                        meatAltLegumeAllocatedServeSize = tokens[38].toDoubleOrNull(),
                        legumeAllocatedMeatAlt = tokens[39].toDoubleOrNull(),
                        dairyAltHeifaScoreMale = tokens[40].toDoubleOrNull(),
                        dairyAltHeifaScoreFemale = tokens[41].toDoubleOrNull(),
                        dairyAltServeSize = tokens[42].toDoubleOrNull(),
                        sodiumHeifaScoreMale = tokens[43].toDoubleOrNull(),
                        sodiumHeifaScoreFemale = tokens[44].toDoubleOrNull(),
                        sodiumMgMilligrams = tokens[45].toDoubleOrNull(),
                        alcoholHeifaScoreMale = tokens[46].toDoubleOrNull(),
                        alcoholHeifaScoreFemale = tokens[47].toDoubleOrNull(),
                        alcoholStandardDrinks = tokens[48].toDoubleOrNull(),
                        waterHeifaScoreMale = tokens[49].toDoubleOrNull(),
                        waterHeifaScoreFemale = tokens[50].toDoubleOrNull(),
                        water = tokens[51].toDoubleOrNull(),
                        waterTotalMl = tokens[52].toDoubleOrNull(),
                        beverageTotalMl = tokens[53].toDoubleOrNull(),
                        sugarHeifaScoreMale = tokens[54].toDoubleOrNull(),
                        sugarHeifaScoreFemale = tokens[55].toDoubleOrNull(),
                        sugar = tokens[56].toDoubleOrNull(),
                        saturatedFatHeifaScoreMale = tokens[57].toDoubleOrNull(),
                        saturatedFatHeifaScoreFemale = tokens[58].toDoubleOrNull(),
                        saturatedFat = tokens[59].toDoubleOrNull(),
                        unsaturatedFatHeifaScoreMale = tokens[60].toDoubleOrNull(),
                        unsaturatedFatHeifaScoreFemale = tokens[61].toDoubleOrNull(),
                        unsaturatedFatServeSize = tokens[62].toDoubleOrNull()
                    )
                    patients.add(patient)
                }
            }
            PatientRepository.insertAll(patients)
        }
    }

}