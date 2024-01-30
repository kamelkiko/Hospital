package com.ashraf.hospital.ui.screen.report.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.composable.ColorBackground
import com.ashraf.hospital.ui.composable.PrimaryButton
import com.ashraf.hospital.ui.theme.ContentTernary
import com.ashraf.hospital.ui.theme.Primary
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.EventHandler
import com.ashraf.hospital.ui.util.LocalNavigationProvider

@Composable
fun CreateReportScreen(viewModel: CreateReportViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    CreateReportContent(
        state,
        viewModel::onNameChanged,
        viewModel::onDescChanged,
        viewModel::addReport
    )
    EventHandler(effects = viewModel.effect) { effect, nav ->
        when (effect) {
            CreateReportUiEffect.Back -> nav.popBackStack()
            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateReportContent(
    state: CreateReportUiState,
    onNameChanged: (String) -> Unit,
    onDescChanged: (String) -> Unit,
    onClick: () -> Unit,
) {
    val nav = LocalNavigationProvider.current
    ColorBackground(color = White) {
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
                    ) { nav.popBackStack() },
                tint = Color.Black
            )
            Text(
                text = stringResource(R.string.create),
                color = Color.Black,
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
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(16.dp)
                .align(Alignment.Center),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.name,
                onValueChange = onNameChanged,
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text(
                        text = stringResource(R.string.name),
                        color = ContentTernary,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = ContentTernary
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
                value = state.desc,
                onValueChange = onDescChanged,
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text(
                        text = stringResource(R.string.description),
                        color = ContentTernary,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = ContentTernary
                )
            )
        }
        PrimaryButton(
            text = stringResource(R.string.create),
            onClick = onClick,
            modifier = Modifier
                .padding(16.dp)
                .align(
                    Alignment.BottomCenter
                )
        )
    }
}