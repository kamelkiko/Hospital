package com.ashraf.hospital.ui.screen.profile

import com.ashraf.hospital.ui.base.ErrorUiState

data class ProfileUiState(
    val name: String = "N/A",
    val type: String = "N/A",
    val id: Int = 0,
    val gender: String = "N/A",
    val birthdayDate: String = "N/A",
    val address: String = "N/A",
    val status: String = "N/A",
    val email: String = "N/A",
    val phone: String = "N/A",
    val isLoading: Boolean = true,
    val error: ErrorUiState? = null,
    val errorMessage: String = "",
)
