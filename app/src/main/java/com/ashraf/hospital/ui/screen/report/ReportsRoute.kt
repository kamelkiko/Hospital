package com.ashraf.hospital.ui.screen.report

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.reportsRoute() {
    composable(route = Screen.Reports.route) {
        ReportsScreen()
    }
}

fun NavController.navigateToReports(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Reports.route, builder = builder)
}