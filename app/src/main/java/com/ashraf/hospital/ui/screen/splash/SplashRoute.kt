package com.ashraf.hospital.ui.screen.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.splashRoute() {
    composable(route = Screen.Splash.route) {
        SplashScreen()
    }
}