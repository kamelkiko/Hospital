package com.ashraf.hospital.ui.screen.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.welcomeRoute() {
    composable(route = Screen.Welcome.route) {
        WelcomeScreen()
    }
}

fun NavController.navigateToWelcome(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Welcome.route, builder = builder)
}