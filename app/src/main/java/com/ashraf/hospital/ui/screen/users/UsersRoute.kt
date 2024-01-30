package com.ashraf.hospital.ui.screen.users

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.usersRoute() {
    composable(route = Screen.Employee.route) {
        UsersScreen()
    }
}

fun NavController.navigateToUsers(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Employee.route, builder = builder)
}