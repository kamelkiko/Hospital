package com.ashraf.hospital.ui.screen.report

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.composable.Loading
import com.ashraf.hospital.ui.screen.report.create.navigateToCreateReport
import com.ashraf.hospital.ui.theme.ContentPrimary
import com.ashraf.hospital.ui.theme.Primary
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.LocalNavigationProvider

@Composable
fun ReportsScreen(viewModel: ReportsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    viewModel.getAllReports()
    AnimatedVisibility(visible = state.isLoading) {
        Loading()
    }
    AnimatedVisibility(visible = !state.isLoading) {
        ReportsContent(state)
    }

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isNotEmpty())
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ReportsContent(state: ReportsUiState) {
    val nav = LocalNavigationProvider.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { nav.navigateToCreateReport() }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.reports) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                    elevation = CardDefaults.cardElevation(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_insert_drive_file_24),
                                contentDescription = stringResource(R.string.report),
                                modifier = Modifier
                                    .size(24.dp),
                                tint = Primary
                            )
                            Text(
                                text = it.name,
                                color = ContentPrimary,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            )
                        }
                        Text(
                            text = if (it.isDone) stringResource(R.string.finished) else stringResource(
                                R.string.process
                            ),
                            color = Primary,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = stringResource(R.string.date_of_birth),
                            modifier = Modifier
                                .size(24.dp),
                            tint = Primary
                        )
                        Text(
                            text = it.date,
                            color = ContentPrimary,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        )
                    }
                }
            }
        }
    }
}