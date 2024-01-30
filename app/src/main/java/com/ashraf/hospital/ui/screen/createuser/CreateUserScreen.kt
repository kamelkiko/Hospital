package com.ashraf.hospital.ui.screen.createuser

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.composable.ColorBackground
import com.ashraf.hospital.ui.composable.DropDownTextField
import com.ashraf.hospital.ui.composable.GradientBackground
import com.ashraf.hospital.ui.composable.PasswordTexTField
import com.ashraf.hospital.ui.composable.PrimaryButton
import com.ashraf.hospital.ui.composable.PrimaryTextField
import com.ashraf.hospital.ui.screen.util.Gender
import com.ashraf.hospital.ui.screen.util.Rule
import com.ashraf.hospital.ui.screen.util.Status
import com.ashraf.hospital.ui.theme.ContentSecondary
import com.ashraf.hospital.ui.theme.White
import com.ashraf.hospital.ui.util.LocalNavigationProvider

@Composable
fun CreateUserScreen(viewModel: CreateUserViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    CreateUserContent(state = state, interaction = viewModel as CreateUserInteractionListener)
    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isNotEmpty())
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun CreateUserContent(
    state: CreateUserUiState,
    interaction: CreateUserInteractionListener
) {
    val context = LocalContext.current
    val navController = LocalNavigationProvider.current
    var date by remember { mutableStateOf("") }
    ColorBackground(color = White) {
        GradientBackground(Modifier.align(Alignment.TopStart))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                        ) { navController.popBackStack() }
                )
                Text(
                    text = stringResource(R.string.new_user),
                    color = ContentSecondary,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
            }
            PrimaryTextField(
                text = state.user.firstName,
                onTextChanged = interaction::onFirstNameChanged,
                icon = painterResource(id = R.drawable.user),
                contentDescription = stringResource(R.string.first_name),
                placeHolder = stringResource(R.string.first_name),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                errorMessage = state.fieldsError.firstNameError,
            )
            PrimaryTextField(
                text = state.user.lastName,
                onTextChanged = interaction::onLastNameChanged,
                icon = painterResource(id = R.drawable.user),
                contentDescription = stringResource(R.string.last_name),
                placeHolder = stringResource(R.string.last_name),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                errorMessage = state.fieldsError.lastNameError,
            )
            DropDownTextField(
                options = listOf(Gender.MALE.type, Gender.FEMALE.type),
                icon = painterResource(id = R.drawable.gender),
                contentDescription = stringResource(R.string.gender),
                onClick = interaction::onGenderChanged
            )
            DropDownTextField(
                options = listOf(
                    Rule.HR.type,
                    Rule.MANAGER.type,
                    Rule.RECEPTIONIST.type,
                    Rule.DOCTOR.type,
                    Rule.NURSE.type
                ),
                icon = painterResource(id = R.drawable.aid_kit),
                contentDescription = stringResource(R.string.specialist),
                onClick = interaction::onSpecialistChanged
            )
            PrimaryTextField(
                text = date,
                onTextChanged = { date = it },
                icon = painterResource(id = R.drawable.calendar),
                contentDescription = stringResource(R.string.date_of_birth),
                placeHolder = stringResource(R.string.date_of_birth),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
            DropDownTextField(
                options = listOf(Status.SINGLE.type, Status.MARRIED.type),
                icon = painterResource(id = R.drawable.heart),
                contentDescription = stringResource(R.string.status),
                onClick = interaction::onStatusChanged
            )
            PrimaryTextField(
                text = state.user.phoneNumber,
                onTextChanged = interaction::onPhoneNumberChanged,
                icon = painterResource(id = R.drawable.call),
                contentDescription = stringResource(R.string.phone_number),
                placeHolder = stringResource(R.string.phone_number),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                errorMessage = state.fieldsError.phoneNumberError,
            )
            PrimaryTextField(
                text = state.user.email,
                onTextChanged = interaction::onEmailChanged,
                icon = painterResource(id = R.drawable.mail),
                contentDescription = stringResource(R.string.email),
                placeHolder = stringResource(R.string.email),
                errorMessage = state.fieldsError.emailError,
            )
            PrimaryTextField(
                text = state.user.address,
                onTextChanged = interaction::onAddressChanged,
                icon = painterResource(id = R.drawable.marker),
                contentDescription = stringResource(R.string.address),
                placeHolder = stringResource(R.string.address),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                errorMessage = state.fieldsError.addressError,
            )
            PasswordTexTField(
                password = state.user.password,
                onPasswordChanged = interaction::onPasswordChanged,
                errorMessage = state.fieldsError.passwordError,
            )
            PrimaryButton(
                text = stringResource(R.string.create_user),
                onClick = {
                    Toast.makeText(context, "User has been created", Toast.LENGTH_SHORT).show()
                    interaction.onClickCreateUser()
                    navController.popBackStack()
                }
            )
        }
    }
}