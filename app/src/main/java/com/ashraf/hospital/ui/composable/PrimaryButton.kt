package com.ashraf.hospital.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.theme.ContentTernary
import com.ashraf.hospital.ui.theme.Primary
import com.ashraf.hospital.ui.theme.White

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Primary,
    textColor:Color= White,
    isEnabled: Boolean = true,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        onClick = onClick,
        enabled = isEnabled
    ) {
        Text(
            text = text,
            color = if (isEnabled) textColor else ContentTernary,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontSize = 16.sp,
        )
    }
}