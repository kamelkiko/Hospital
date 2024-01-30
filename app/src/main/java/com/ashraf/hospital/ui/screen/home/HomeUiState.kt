package com.ashraf.hospital.ui.screen.home

import com.ashraf.hospital.ui.base.ErrorUiState

data class HomeUiState(
    val name: String = "N/A",
    val type: String = "N/A",
    val id: Int = 0,
    val isLoading: Boolean = true,
    val error: ErrorUiState? = null,
    val errorMessage: String = "",
)
