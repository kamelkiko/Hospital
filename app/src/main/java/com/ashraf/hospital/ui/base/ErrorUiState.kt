package com.ashraf.hospital.ui.base

sealed interface ErrorUiState {
    data class NoInternet(val errorMessage: String) : ErrorUiState
    data class UnAuthorized(val errorMessage: String) : ErrorUiState
    data class ServerError(val errorMessage: String) : ErrorUiState
    data class UnknownError(val errorMessage: String) : ErrorUiState
    data class NotFound(val errorMessage: String) : ErrorUiState
    data class UserNotFound(val errorMessage: String) : ErrorUiState
    data class UserAlreadyExist(val errorMessage: String) : ErrorUiState
    data class EmptyInputData(val errorMessage: String) : ErrorUiState
    data class InvalidEmail(val errorMessage: String) : ErrorUiState
    data class InvalidPassword(val errorMessage: String) : ErrorUiState
    data class InvalidFirstName(val errorMessage: String) : ErrorUiState
    data class InvalidLastName(val errorMessage: String) : ErrorUiState
    data class InvalidPhoneNumber(val errorMessage: String) : ErrorUiState
    data class InvalidAddress(val errorMessage: String) : ErrorUiState
    data class NoData(val errorMessage: String) : ErrorUiState
}