package com.ashraf.hospital.ui.screen.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.composable.ColorBackground
import com.ashraf.hospital.ui.composable.GradientBackground
import com.ashraf.hospital.ui.composable.LogoImage
import com.ashraf.hospital.ui.navigation.Screen
import com.ashraf.hospital.ui.screen.home.navigateToHome
import com.ashraf.hospital.ui.screen.login.navigateToLogin
import com.ashraf.hospital.ui.theme.Disabled
import com.ashraf.hospital.ui.theme.Primary
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.LocalNavigationProvider
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    ColorBackground(
        color = White
    ) {
        val navController = LocalNavigationProvider.current
        var progress by remember { mutableFloatStateOf(0.2f) }
        GradientBackground(Modifier.align(Alignment.TopStart))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            LogoImage()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    trackColor = Disabled,
                    color = Primary,
                    strokeCap = StrokeCap.Butt,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.loading),
                    color = Primary,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        LaunchedEffect(key1 = progress) {
            delay(1.seconds)
            if (progress <= 1f) progress += 0.2f
            if (progress == 1f) {
                if (state.isLoggedIn)
                    navController.navigateToHome {
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                    } else
                    navController.navigateToLogin {
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                    }
            }
        }
    }
}