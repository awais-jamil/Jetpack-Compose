package com.awais.android

// create composable  screen for login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val showPassword = remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(100.dp))
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Login",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 20.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp)
                .padding(bottom = 10.dp)
                .padding(top = 10.dp)
    
                .padding(vertical = 10.dp)
        
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 20.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp)
                .padding(bottom = 10.dp)
                .padding(top = 10.dp)
    
                .padding(vertical = 10.dp)
        
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /* Handle login button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
//                .background(Color.Black)
                .padding(horizontal = 20.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp)
        
        ) {
            Text(text = "Login", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Don't have an account?",
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Sign up",
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        
        )
    }
    
}
