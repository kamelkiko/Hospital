package com.ashraf.hospital.ui.screen.createuser

import com.ashraf.hospital.ui.base.ErrorUiState
import com.ashraf.hospital.ui.screen.util.Gender
import com.ashraf.hospital.ui.screen.util.Rule
import com.ashraf.hospital.ui.screen.util.Status

data class CreateUserUiState(
    val isLoading: Boolean = false,
    val error: ErrorUiState? = null,
    val user: UserUiState = UserUiState(),
    val errorMessage: String = "",
    val fieldsError: SignupFieldsErrorUiState = SignupFieldsErrorUiState(),
)

data class UserUiState(
    val email: String = "",
    val password: String = "",
    val address: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val gender: String = Gender.MALE.type,
    val status: String = Status.SINGLE.type,
    val specialist: String = Rule.DOCTOR.type,
)

data class SignupFieldsErrorUiState(
    val emailError: String = "",
    val passwordError: String = "",
    val addressError: String = "",
    val firstNameError: String = "",
    val lastNameError: String = "",
    val phoneNumberError: String = "",
)
