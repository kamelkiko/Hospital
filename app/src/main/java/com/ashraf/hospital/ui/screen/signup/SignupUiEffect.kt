package com.ashraf.hospital.ui.screen.signup

sealed interface SignupUiEffect {
    data object NavigateToHomeScreen : SignupUiEffect
}