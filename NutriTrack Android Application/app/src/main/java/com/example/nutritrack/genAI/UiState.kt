package com.example.nutritrack.genAI

sealed interface UiState {

    // Empty initial state when genai is first loading.
    object Initial : UiState

    // Loading
    object Loading : UiState

    // Text has been successfully generated
    data class Success(val outputText: String) : UiState

    // Error message for issues when generating text
    data class Error(val errorMesage: String) : UiState
}