package com.ashraf.hospital.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashraf.hospital.R
import com.ashraf.hospital.ui.theme.ContentPrimary
import com.ashraf.hospital.ui.theme.Primary

@Composable
fun ProfileItem(
    title: String,
    icon: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(24.dp),
            tint = Primary
        )
        Text(
            text = title,
            color = ContentPrimary,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontSize = 16.sp,
        )
    }
}