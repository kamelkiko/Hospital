package com.ashraf.hospital.ui.screen.home

sealed interface HomeUiEffect {
    data object NavigateToProfileScreen : HomeUiEffect
//    data object NavigateToRegisterScreen : HomeUiEffect
}