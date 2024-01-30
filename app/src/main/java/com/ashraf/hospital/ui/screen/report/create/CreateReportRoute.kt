package com.ashraf.hospital.ui.screen.report.create

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ashraf.hospital.ui.navigation.Screen

fun NavGraphBuilder.createReportRoute() {
    composable(route = Screen.CreateReport.route) {
        CreateReportScreen()
    }
}

fun NavController.navigateToCreateReport(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.CreateReport.route, builder = builder)
}