package com.example.fetchexercise.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchexercise.network.*
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UiState {
    data class Success(val data: ItemMap) : UiState
    object Error : UiState
    object Loading : UiState
}

class ViewModel : ViewModel() {
    var result: ItemMap = mutableMapOf()
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getData()

                uiState = UiState.Success(rawListToMap(listResult))
            } catch (e: IOException) {
                uiState = UiState.Error
            }
        }
    }
}