package com.awais.android.features.auth.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.awais.android.features.auth.presentation.viewmodels.AuthViewModel
import com.awais.ui.CustomTextField
import com.awais.ui.PrimaryButton
import com.awais.ui.TextButton

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    
    val tag = "LoginScreen"
    
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    
    if (isLoggedIn) {
        navigateToHome()
    }
    
    val loading by viewModel.loading.collectAsState()
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Text(
                text = "Login",
                style = MaterialTheme.typography.displayLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(bottom = 40.dp)
            )
            
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email.value,
                hintText = "Enter email",
                labelText = "Email",
                maxLines = 1,
                onValueChange = {
                    email.value = it
                },
                validator = {
                    if (it.isEmpty()) {
                        "Email cannot be empty"
                    } else {
                        ""
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Person icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password.value,
                hintText = "Enter password",
                labelText = "Password",
                maxLines = 1,
                isPassword = true,
                onValueChange = {
                    password.value = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Person icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text("Login")
                },
                enabled = email.value.isNotEmpty() || password.value.isNotEmpty(),
                loading = loading,
                onClick = {
                    Log.d(tag, "Login clicked")
                    viewModel.login(email.value, password.value)
                }
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row {
                Text("Don't have an account?")
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    modifier = Modifier,
                    title = "SignUp",
                    onClick = {
                        Log.d(tag, "SignUp clicked")
                        navigateToSignUp()
                    }
                )
            }
        }
    }
}
