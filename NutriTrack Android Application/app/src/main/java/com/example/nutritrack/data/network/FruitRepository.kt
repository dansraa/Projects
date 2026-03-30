package com.example.nutritrack.data.network

class FruitRepository() {
    private val apiService = APIService.create()

    suspend fun searchFruitName(name: String): FruitResponse {
        return apiService.searchFruitName(name)
    }
}