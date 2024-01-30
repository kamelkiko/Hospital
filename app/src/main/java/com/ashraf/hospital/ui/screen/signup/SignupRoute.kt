package com.ashraf.hospital.ui.screen.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.signupRoute() {
    composable(route = Screen.Signup.route) {
        SignupScreen()
    }
}

fun NavController.navigateToSignup(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Signup.route, builder = builder)
}