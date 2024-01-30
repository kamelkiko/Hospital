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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.theme.ContentTernary
import com.ashraf.hospital.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    icon: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
    placeHolder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = onTextChanged,
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = placeHolder,
                    color = ContentTernary,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                )
            },
            leadingIcon = {
                Icon(
                    painter = icon,
                    contentDescription = contentDescription,
                    tint = Primary,
                    modifier = Modifier.size(24.dp),
                )
            },
            keyboardOptions = keyboardOptions,
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = ContentTernary
            )
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