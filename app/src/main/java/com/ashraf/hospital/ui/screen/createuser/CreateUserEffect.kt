package com.ashraf.hospital.ui.screen.createuser

sealed interface CreateUserEffect {
    data object Back : CreateUserEffect
}