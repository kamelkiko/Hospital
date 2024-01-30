package com.ashraf.hospital.ui.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcomeScreen")
    data object Login : Screen("loginScreen")
    data object Signup : Screen("signupScreen")
    data object Home : Screen("homeScreen")
    data object Splash : Screen("SplashScreen")
    data object Profile : Screen("ProfileScreen")
    data object CreateReport : Screen("CreateReportScreen")
    data object Reports : Screen("ReportsScreen")
    data object Employee : Screen("EmployeeScreen")
    data object Create : Screen("CreateScreen")
}