package com.example.nutritrack.data.network

data class FruitResponse(
    val name: String?,
    val family: String?,
    val nutritions: Nutritions
)

data class Nutritions(
    val calories: Int?,
    val fat: Double?,
    val sugar: Double?,
    val carbohydrates: Double?,
    val protein: Double?
)
