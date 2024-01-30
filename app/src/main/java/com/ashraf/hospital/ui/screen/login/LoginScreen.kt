package com.ashraf.hospital.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.composable.ColorBackground
import com.ashraf.hospital.ui.composable.GradientBackground
import com.ashraf.hospital.ui.composable.LogoImage
import com.ashraf.hospital.ui.composable.PasswordTexTField
import com.ashraf.hospital.ui.composable.PrimaryButton
import com.ashraf.hospital.ui.composable.PrimaryTextField
import com.ashraf.hospital.ui.navigation.Screen
import com.ashraf.hospital.ui.screen.home.navigateToHome
import com.ashraf.hospital.ui.screen.signup.navigateToSignup
import com.ashraf.hospital.ui.theme.ContentTernary
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.EventHandler

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    LoginContent(state, viewModel as LoginInteractionListener)
    EventHandler(effects = viewModel.effect) { effect, nav ->
        when (effect) {
            LoginUiEffect.NavigateToHomeScreen -> nav.navigateToHome {
                popUpTo(Screen.Login.route) {
                    inclusive = true
                }
            }

            LoginUiEffect.NavigateToRegisterScreen -> nav.navigateToSignup()
            else -> {}
        }
    }
    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isNotEmpty())
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun LoginContent(
    state: LoginUiState,
    interaction: LoginInteractionListener
) {
    ColorBackground(color = White) {
        GradientBackground(Modifier.align(Alignment.TopStart))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LogoImage()
            Text(
                text = stringResource(R.string.to_continue_login_now),
                color = ContentTernary,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                PrimaryTextField(
                    text = state.email,
                    onTextChanged = interaction::onEmailChanged,
                    icon = painterResource(id = R.drawable.mail),
                    contentDescription = stringResource(R.string.email),
                    placeHolder = stringResource(R.string.email),
                    errorMessage = state.emailError
                )
                PasswordTexTField(
                    password = state.password,
                    onPasswordChanged = interaction::onPasswordChanged,
                    errorMessage = state.passwordError
                )
                PrimaryButton(
                    text = stringResource(R.string.login),
                    onClick = interaction::onClickLogin
                )
                PrimaryButton(
                    text = stringResource(R.string.signup),
                    onClick = interaction::onClickSignup
                )
            }
        }
    }
}
