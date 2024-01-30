package com.ashraf.hospital.ui.screen.login

sealed interface LoginUiEffect {
    data object NavigateToHomeScreen : LoginUiEffect
    data object NavigateToRegisterScreen : LoginUiEffect
}