package com.ashraf.hospital.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {
        HomeScreen()
    }
}

fun NavController.navigateToHome(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Home.route, builder = builder)
}