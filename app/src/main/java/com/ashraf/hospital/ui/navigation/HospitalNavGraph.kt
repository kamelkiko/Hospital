package com.ashraf.hospital.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.ashraf.hospital.ui.util.LocalNavigationProvider

@Composable
fun HospitalNavGraph() {
    val navController = LocalNavigationProvider.current
    NavHost(
        navController = navController,
        startDestination = Graph.AUTH
    ) {
        authGraph()
    }
}