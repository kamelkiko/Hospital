package com.ashraf.hospital.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.ashraf.hospital.ui.navigation.HospitalNavGraph
import com.ashraf.hospital.ui.util.LocalNavigationProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CompositionLocalProvider(LocalNavigationProvider provides navController) {
                HospitalNavGraph()
            }
        }
    }
}
