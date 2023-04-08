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
    data class Success(val data: String) : UiState
    object Error : UiState
    object Loading : UiState
}

class ViewModel : ViewModel() {
    var res: ItemMap = mutableMapOf()
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getData()
                res = convertRawListToMap(listResult)
                uiState = UiState.Success(
                    ""
                )
            } catch (e: IOException) {
                uiState = UiState.Error
            }
        }
    }
}