package com.ashraf.hospital.ui.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.loginRoute() {
    composable(route = Screen.Login.route) {
        LoginScreen()
    }
}

fun NavController.navigateToLogin(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Login.route, builder = builder)
}