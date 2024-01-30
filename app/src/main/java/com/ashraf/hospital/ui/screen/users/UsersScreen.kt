package com.ashraf.hospital.ui.screen.users

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.screen.createuser.navigateToCreateUser
import com.ashraf.hospital.ui.theme.ContentPrimary
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.LocalNavigationProvider

@Composable
fun UsersScreen(viewModel: UsersViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    viewModel.getAllUsers()
    UsersContent(state)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun UsersContent(state: UsersUiState) {
    val nav = LocalNavigationProvider.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { nav.navigateToCreateUser() }) {
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
            items(state.users) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                    elevation = CardDefaults.cardElevation(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        Text(
                            text = it.name,
                            color = ContentPrimary,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        )
                        Text(
                            text = it.job,
                            color = ContentPrimary,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        )
                    }
                }
            }
        }
    }
}