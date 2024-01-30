package com.ashraf.hospital.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ashraf.hospital.R

@Composable
fun LogoImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(R.string.logo),
        modifier = modifier
            .fillMaxSize(0.4f)
    )
}