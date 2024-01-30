package com.ashraf.hospital.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import com.ashraf.hospital.R

@Composable
fun GradientBackground(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .paint(painterResource(id = R.drawable.gradient))
    )
}