package com.ashraf.hospital.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.profileRoute() {
    composable(route = Screen.Profile.route) {
        ProfileScreen()
    }
}

fun NavController.navigateToProfile(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Profile.route, builder = builder)
}