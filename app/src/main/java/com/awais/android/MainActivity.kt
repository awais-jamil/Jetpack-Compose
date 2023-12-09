package com.awais.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.awais.android.presentation.screens.LoginScreen
import com.awais.android.presentation.viewmodels.AuthViewModel
import com.awais.android.theme.JetpackProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            JetpackProjectTheme {
                LoginScreen(AuthViewModel())
            }
        }
    }
}


