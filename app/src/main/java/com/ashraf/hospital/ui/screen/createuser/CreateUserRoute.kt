package com.ashraf.hospital.ui.screen.createuser

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.createUserRoute() {
    composable(route = Screen.Create.route) {
        CreateUserScreen()
    }
}

fun NavController.navigateToCreateUser(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Create.route, builder = builder)
}