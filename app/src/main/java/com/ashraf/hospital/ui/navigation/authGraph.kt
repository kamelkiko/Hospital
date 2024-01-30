package com.ashraf.hospital.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.ashraf.hospital.ui.screen.createuser.createUserRoute
import com.ashraf.hospital.ui.screen.home.homeRoute
import com.ashraf.hospital.ui.screen.login.loginRoute
import com.ashraf.hospital.ui.screen.profile.profileRoute
import com.ashraf.hospital.ui.screen.report.create.createReportRoute
import com.ashraf.hospital.ui.screen.report.reportsRoute
import com.ashraf.hospital.ui.screen.signup.signupRoute
import com.ashraf.hospital.ui.screen.splash.splashRoute
import com.ashraf.hospital.ui.screen.users.usersRoute
import com.ashraf.hospital.ui.screen.welcome.welcomeRoute

fun NavGraphBuilder.authGraph() {
    navigation(
        startDestination = Screen.Splash.route,
        route = Graph.AUTH
    ) {
        splashRoute()
        welcomeRoute()
        loginRoute()
        signupRoute()
        homeRoute()
        profileRoute()
        createReportRoute()
        reportsRoute()
        usersRoute()
        createUserRoute()
    }
}