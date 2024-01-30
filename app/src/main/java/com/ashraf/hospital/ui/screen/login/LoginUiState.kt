package com.ashraf.hospital.ui.screen.login

import com.ashraf.hospital.ui.base.ErrorUiState

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: ErrorUiState? = null,
    val errorMessage: String = "",
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
)
