package com.awais.android.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            // create top bar
        },
        bottomBar = {
            // create bottom bar
        },
        content = {
            Text(
                modifier = Modifier.padding(it),
                text = "Home Screen"
            )
        }
    )
}