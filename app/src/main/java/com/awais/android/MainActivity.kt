package com.awais.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.awais.android.theme.JetpackProjectTheme
import com.awais.ui.CustomTextField
import com.awais.ui.PrimaryButton
import com.awais.ui.TextButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            JetpackProjectTheme {
                val text = remember { mutableStateOf("") }
                val password = remember { mutableStateOf("") }
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
                        CustomTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = text.value,
                            hintText = "Enter email",
                            labelText = "Email",
                            maxLines = 1,
                            onValueChange = {
                                text.value = it
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
                            content = { Text("Login") },
                            onClick = {
                                Log.d("MainActivity", "onClick")
                            }
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Row {
                            Text("Already have an account?")
                            Spacer(modifier = Modifier.width(8.dp))
                            TextButton(
                                modifier = Modifier,
                                title = "SignUp",
                                onClick = {
                                    Log.d("MainActivity", "onClick")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

