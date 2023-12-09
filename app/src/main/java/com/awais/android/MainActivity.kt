package com.awais.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.awais.android.theme.JetpackProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            JetpackProjectTheme {
                val text = remember { mutableStateOf("") }
                val password = remember { mutableStateOf("") }
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(horizontal = 16.dp),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        CustomTextField(
//                            modifier = Modifier.fillMaxWidth(),
//                            value = text.value,
//                            hintText = "Enter email",
//                            labelText = "Email",
//                            maxLines = 1,
//                            onValueChange = {
//                                text.value = it
//                            },
//                            validator = {
//                                if (it.isEmpty()) {
//                                    "Email cannot be empty"
//                                } else {
//                                    ""
//                                }
//                            },
//                            leadingIcon = {
//                                Icon(
//                                    imageVector = Icons.Filled.Email,
//                                    contentDescription = "Person icon",
//                                    tint = MaterialTheme.colorScheme.primary
//                                )
//                            },
//                        )
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        CustomTextField(
//                            modifier = Modifier.fillMaxWidth(),
//                            value = password.value,
//                            hintText = "Enter password",
//                            labelText = "Password",
//                            maxLines = 1,
//                            isPassword = true,
//                            onValueChange = {
//                                password.value = it
//                            },
//                            leadingIcon = {
//                                Icon(
//                                    imageVector = Icons.Filled.Lock,
//                                    contentDescription = "Person icon",
//                                    tint = MaterialTheme.colorScheme.primary
//                                )
//                            },
//                        )
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        PrimaryButton(
//                            modifier = Modifier.fillMaxWidth(),
//                            content = { Text("Login") },
//                            onClick = {
//                                Log.d("MainActivity", "onClick")
//                                //navigate to LoginScreen
//
//
//                            }
//                        )
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        Row {
//                            Text("Already have an account?")
//                            Spacer(modifier = Modifier.width(8.dp))
//                            TextButton(
//                                modifier = Modifier,
//                                title = "SignUp",
//                                onClick = {
//                                    Log.d("MainActivity", "onClick")
//                                }
//                            )
//                        }
//                    }
//                }
                
                LoginScreen()
            }
        }
    }
}

//write a fun to check if string of parenthesis is valid or not


