package com.ashraf.hospital.ui.screen.profile

sealed interface ProfileUiEffect {
    data object NavigateToLoginScreen : ProfileUiEffect
}