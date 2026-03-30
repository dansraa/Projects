package com.example.nutritrack.data.patient

import androidx.room.Entity
import androidx.room.PrimaryKey

// Patient table within the database.

@Entity(tableName = "patients")
data class Patient (

    val name: String? = null, // Nullable name due to not being set on creation.
    val password: String? = null, // Nullable password due to not being set on creation.
    val phoneNo: String, // Patients phone number, value is a string.
    // Tables primary key, which auto generates upon new entries within the database.
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val patientSex: String, // Patients sex/gender, value is a string.

    // Storing heifa scores into 'Double?', which is used for allowing nulls in case there
    // are instances where fields are missing/optional.

    // Main Heifa Scores
    val heifaTotalScoreMale: Double?,
    val heifaTotalScoreFemale: Double?,
    val discretionaryHeifaScoreMale: Double?,
    val discretionaryHeifaScoreFemale: Double?,
    val discretionaryServeSize: Double?,

    // Vegetable Heifa Scores
    val vegeHeifaScoreMale: Double?,
    val vegeHeifaScoreFemale: Double?,
    val vegeLegumesServeSize: Double?,
    val legumesAllocatedVege: Double?,
    val vegeVariationsScore: Double?,
    val vegeCruciferous: Double?,
    val vegeTuberAndBulb: Double?,
    val vegeOther: Double?,
    val legumes: Double?,
    val vegeGreen: Double?,
    val vegeRedAndOrange: Double?,

    // Fruit Heifa Scores
    val fruitHeifaScoreMale: Double?,
    val fruitHeifaScoreFemale: Double?,
    val fruitServeSize: Double?,
    val fruitVariationsScore: Double?,
    val fruitPome: Double?,
    val fruitTropicalAndSubtropical: Double?,
    val fruitBerry: Double?,
    val fruitStone: Double?,
    val fruitCitrus: Double?,
    val fruitOther: Double?,

    // Grain/Cereals Heifa Scores
    val grainCerealHeifaScoreMale: Double?,
    val grainCerealHeifaScoreFemale: Double?,
    val grainCerealServeSize: Double?,
    val grainCerealNonWholeGrain: Double?,
    val wholeGrainHeifaScoreMale: Double?,
    val wholeGrainHeifaScoreFemale: Double?,
    val wholeGrainServeSize: Double?,

    // Meat/alt Heifa Scores
    val meatAltHeifaScoreMale: Double?,
    val meatAltHeifaScoreFemale: Double?,
    val meatAltLegumeAllocatedServeSize: Double?,
    val legumeAllocatedMeatAlt: Double?,

    // Dairy Heifa Scores
    val dairyAltHeifaScoreMale: Double?,
    val dairyAltHeifaScoreFemale: Double?,
    val dairyAltServeSize: Double?,

    // Sodium Heifa Scores
    val sodiumHeifaScoreMale: Double?,
    val sodiumHeifaScoreFemale: Double?,
    val sodiumMgMilligrams: Double?,

    // Alcohol Heifa Scores
    val alcoholHeifaScoreMale: Double?,
    val alcoholHeifaScoreFemale: Double?,
    val alcoholStandardDrinks: Double?,

    // Water Heifa Scores
    val waterHeifaScoreMale: Double?,
    val waterHeifaScoreFemale: Double?,
    val water: Double?,
    val waterTotalMl: Double?,
    val beverageTotalMl: Double?,

    // Sugar Heifa Scores
    val sugarHeifaScoreMale: Double?,
    val sugarHeifaScoreFemale: Double?,
    val sugar: Double?,

    // Saturated Fat Heifa Scores
    val saturatedFatHeifaScoreMale: Double?,
    val saturatedFatHeifaScoreFemale: Double?,
    val saturatedFat: Double?,

    // Unsaturated Fat Heifa Scores
    val unsaturatedFatHeifaScoreMale: Double?,
    val unsaturatedFatHeifaScoreFemale: Double?,
    val unsaturatedFatServeSize: Double?
)

// Data class for account info query for specific name, phone number and userid columns.
data class AccountInfo(
    val name: String,
    val phoneNo: String,
    val userId: Int
)

// Data class used to stores the male or female heifa scores for insights page.
data class PatientHeifaScores(
    val heifaTotalScore: Double?,
    val heifaDiscretionaryScore: Double?,
    val vegeHeifaScore: Double?,
    val fruitHeifaScore: Double?,
    val grainCerealHeifaScore: Double?,
    val wholeGrainHeifaScore: Double?,
    val meatAltHeifaScore: Double?,
    val dairyAltHeifaScore: Double?,
    val sodiumHeifaScore: Double?,
    val alcoholHeifaScore: Double?,
    val waterHeifaScore: Double?,
    val sugarHeifaScore: Double?,
    val saturatedFatHeifaScore: Double?,
    val unsaturatedFatHeifaScore: Double?
)