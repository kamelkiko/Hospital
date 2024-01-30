package com.ashraf.hospital.ui.screen.profile

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.composable.ColorBackground
import com.ashraf.hospital.ui.composable.Loading
import com.ashraf.hospital.ui.composable.PrimaryButton
import com.ashraf.hospital.ui.composable.ProfileItem
import com.ashraf.hospital.ui.navigation.Screen
import com.ashraf.hospital.ui.screen.login.navigateToLogin
import com.ashraf.hospital.ui.theme.ContentPrimary
import com.ashraf.hospital.ui.theme.Primary
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.EventHandler
import com.ashraf.hospital.ui.util.LocalNavigationProvider

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    AnimatedVisibility(visible = state.isLoading) {
        Loading()
    }
    AnimatedVisibility(visible = !state.isLoading) {
        ProfileContent(state, viewModel::logout)
    }

    EventHandler(effects = viewModel.effect) { effect, nav ->
        when (effect) {
            ProfileUiEffect.NavigateToLoginScreen -> nav.navigateToLogin {
                popUpTo(Screen.Profile.route) {
                    inclusive = true
                }
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
            }

            else -> {}
        }
    }

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isNotEmpty())
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onClick: () -> Unit,
) {
    val navController = LocalNavigationProvider.current
    ColorBackground(color = Primary) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { navController.popBackStack() },
                tint = White
            )
            Text(
                text = stringResource(R.string.my_profile),
                color = White,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = White),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = state.name,
                        color = Primary,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    ProfileItem(
                        title = state.type,
                        icon = painterResource(id = R.drawable.aid_kit),
                        contentDescription = stringResource(
                            id = R.string.specialist
                        )
                    )
                    ProfileItem(
                        title = state.gender,
                        icon = painterResource(id = R.drawable.gender),
                        contentDescription = stringResource(
                            id = R.string.gender
                        )
                    )
                    ProfileItem(
                        title = state.birthdayDate,
                        icon = painterResource(id = R.drawable.calendar),
                        contentDescription = stringResource(
                            id = R.string.date_of_birth
                        )
                    )
                    ProfileItem(
                        title = state.address,
                        icon = painterResource(id = R.drawable.marker),
                        contentDescription = stringResource(
                            id = R.string.address
                        )
                    )
                    ProfileItem(
                        title = state.status,
                        icon = painterResource(id = R.drawable.heart),
                        contentDescription = stringResource(
                            id = R.string.status
                        )
                    )
                    ProfileItem(
                        title = state.email,
                        icon = painterResource(id = R.drawable.mail),
                        contentDescription = stringResource(
                            id = R.string.email
                        )
                    )
                    ProfileItem(
                        title = state.phone,
                        icon = painterResource(id = R.drawable.call),
                        contentDescription = stringResource(
                            id = R.string.phone_number
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(
                containerColor = White,
                text = stringResource(R.string.logout),
                onClick = onClick,
                textColor = ContentPrimary,
            )
        }
    }
}