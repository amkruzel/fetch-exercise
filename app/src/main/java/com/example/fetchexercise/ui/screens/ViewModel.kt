package com.example.fetchexercise.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchexercise.network.Api
import com.example.fetchexercise.network.ListItem
import com.example.fetchexercise.network.formatListItems
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UiState {
    data class Success(val data: String) : UiState
    object Error : UiState
    object Loading : UiState
}

class ViewModel : ViewModel() {
    var res: List<ListItem> = emptyList()
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getData()
                res = formatListItems(listResult)
                uiState = UiState.Success(
                    ""
                )
            } catch (e: IOException) {
                uiState = UiState.Error
            }
        }
    }
}