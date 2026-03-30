package com.example.nutritrack.data.network


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack.data.network.FruitRepository
import com.example.nutritrack.data.network.FruitResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class FruitUiState {
    object Idle : FruitUiState()
    object Loading : FruitUiState()
    data class Success(val fruit: FruitResponse) : FruitUiState()
    data class Error(val message: String) : FruitUiState()
}

class FruitViewModel(
    private val repository: FruitRepository = FruitRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<FruitUiState>(FruitUiState.Idle)
    val uiState: StateFlow<FruitUiState> = _uiState.asStateFlow()

    fun searchFruit(name: String) {
        _uiState.value = FruitUiState.Loading
        viewModelScope.launch {
            try {
                val result = repository.searchFruitName(name.lowercase())
                _uiState.value = FruitUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = FruitUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}