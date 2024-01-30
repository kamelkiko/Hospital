package com.ashraf.hospital.ui.screen.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.composable.ColorBackground
import com.ashraf.hospital.ui.composable.HomeCard
import com.ashraf.hospital.ui.composable.Loading
import com.ashraf.hospital.ui.screen.profile.navigateToProfile
import com.ashraf.hospital.ui.screen.report.navigateToReports
import com.ashraf.hospital.ui.screen.users.navigateToUsers
import com.ashraf.hospital.ui.screen.util.Rule
import com.ashraf.hospital.ui.theme.ContentPrimary
import com.ashraf.hospital.ui.theme.Primary
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.EventHandler
import com.ashraf.hospital.ui.util.LocalNavigationProvider

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    AnimatedVisibility(visible = state.isLoading) {
        Loading()
    }
    AnimatedVisibility(visible = !state.isLoading) {
        HomeContent(state, viewModel as HomeInteractionListener)
    }

    EventHandler(effects = viewModel.effect) { effect, nav ->
        when (effect) {
            HomeUiEffect.NavigateToProfileScreen -> nav.navigateToProfile()
            else -> {}
        }
    }
    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isNotEmpty()) Toast.makeText(
            context, state.errorMessage, Toast.LENGTH_SHORT
        ).show()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HomeContent(
    state: HomeUiState, interaction: HomeInteractionListener
) {
    val nav = LocalNavigationProvider.current
    ColorBackground(color = White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = stringResource(R.string.user),
                    tint = Primary,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() }) { interaction.onClickProfile() },
                )
                Column {
                    Text(
                        text = state.name,
                        color = ContentPrimary,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    )
                    Text(
                        text = "Specialist , ${state.type}",
                        color = Primary,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    )
                }
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 2,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HomeCard(
                    icon = painterResource(id = R.drawable.aid_kit),
                    contentDescription = stringResource(R.string.cases),
                    containerColor = Color(0xFF5F9EDC),
                    title = stringResource(R.string.cases),
                    shape = RoundedCornerShape(16.dp),
                    isVisible =
                    state.type == Rule.DOCTOR.type || state.type == Rule.NURSE.type
                            || state.type == Rule.MANAGER.type,
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .height(178.dp)
                        .clickable { nav.navigateToReports() }
                )
                HomeCard(
                    icon = painterResource(id = R.drawable.check),
                    contentDescription = stringResource(R.string.tasks),
                    containerColor = Color(0xFF5FDC89),
                    title = stringResource(R.string.tasks),
                    shape = RoundedCornerShape(16.dp),
                    isVisible = true,
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .height(178.dp)
                        .clickable { nav.navigateToReports() }
                )
                HomeCard(
                    icon = painterResource(id = R.drawable.baseline_insert_drive_file_24),
                    contentDescription = stringResource(R.string.report),
                    containerColor = Color(0xFF915FDC),
                    title = stringResource(R.string.report),
                    shape = RoundedCornerShape(16.dp),
                    isVisible = true,
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .height(178.dp)
                        .clickable { nav.navigateToReports() }
                )
                HomeCard(
                    icon = painterResource(id = R.drawable.employee),
                    contentDescription = stringResource(R.string.employee),
                    containerColor = Color(0xFFDC915F),
                    title = stringResource(R.string.employee),
                    shape = RoundedCornerShape(16.dp),
                    isVisible = state.type == Rule.HR.type || state.type == Rule.MANAGER.type,
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .height(178.dp)
                        .clickable { nav.navigateToUsers() }
                )
            }
        }
    }
}