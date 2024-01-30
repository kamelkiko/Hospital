package com.ashraf.hospital.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.theme.ContentTernary
import com.ashraf.hospital.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTexTField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
) {
    var showPassword by remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChanged,
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.password),
                    color = ContentTernary,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.lock),
                    contentDescription = stringResource(R.string.password),
                    tint = Primary,
                    modifier = Modifier.size(24.dp),
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon =
            { TrailingIcon(showPassword) { showPassword = !showPassword } },
            visualTransformation =
            if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = ContentTernary
            ),
        )
        AnimatedVisibility(
            visible = isError,
            enter = slideInHorizontally { -it },
            exit = slideOutHorizontally { it }
        ) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
            )
        }
    }
}


@Composable
private fun TrailingIcon(
    showPassword: Boolean,
    togglePasswordVisibility: () -> Unit
) {
    IconButton(onClick = { togglePasswordVisibility() }) {
        Icon(
            painter = if (showPassword) painterResource(id = R.drawable.show_password)
            else painterResource(id = R.drawable.hide_password),
            contentDescription = if (showPassword) stringResource(R.string.show_password) else stringResource(
                R.string.hide_password
            ),
            tint = Primary
        )
    }
}