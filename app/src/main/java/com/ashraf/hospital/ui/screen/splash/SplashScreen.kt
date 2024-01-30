package com.ashraf.hospital.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ashraf.hospital.ui.composable.ColorBackground
import com.ashraf.hospital.ui.composable.GradientBackground
import com.ashraf.hospital.ui.composable.LogoImage
import com.ashraf.hospital.ui.navigation.Screen
import com.ashraf.hospital.ui.screen.welcome.navigateToWelcome
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.LocalNavigationProvider
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

private const val DELAY_TIME = 1.5

@Composable
fun SplashScreen() {
    val navController = LocalNavigationProvider.current
    ColorBackground(
        color = White
    ) {
        GradientBackground(Modifier.align(Alignment.TopStart))
        LogoImage(Modifier.align(Alignment.Center))
        LaunchedEffect(key1 = Unit) {
            delay(DELAY_TIME.seconds)
            navController.navigateToWelcome {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}