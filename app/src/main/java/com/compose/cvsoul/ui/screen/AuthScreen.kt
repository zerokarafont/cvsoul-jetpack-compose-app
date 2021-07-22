package com.compose.cvsoul.ui.screen

import android.text.Layout
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

enum class AuthAction(value: String) {
    LOGIN("login"),
    REGISTER("register")
}

@Composable
fun AuthScreen(navController: NavController) {
    var action      by remember { mutableStateOf("LOGIN") }
    var username    by remember { mutableStateOf("") }
    var password    by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var code        by remember { mutableStateOf("") }

    val isFormValid by remember(username, password, confirmPass, code) {
        derivedStateOf { 
            when(AuthAction.valueOf(action).name) {
                AuthAction.LOGIN.name -> username.isNotEmpty() && password.isNotEmpty()
                AuthAction.REGISTER.name -> username.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty() && code.isNotEmpty()
                else -> false
            }
        }
    }

    @Composable
    fun RegisterPage() {
        Row {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "用户名") }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "密码") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                label = { Text(text = "确认密码") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                label = { Text(text = "邀请码") },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Button(
                enabled = isFormValid,
                onClick = { /*TODO*/ }
            ) {
                Text(text = "注册")
            }
            Text(text = "登录", Modifier.clickable { action = "LOGIN" })
        }
    }
    
    @Composable
    fun LoginPage() {
        Row {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "用户名") }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "密码") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Text(text = "注册", modifier = Modifier.clickable { action = "REGISTER" })
            Button(
                enabled = isFormValid,
                onClick = {}
            ) {
                Text(text = "登录")
            }
        }
    }
    

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
        .fillMaxSize()
        .background(Color(0xCCEEEEEE))
        .padding(50.dp)) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            when(AuthAction.valueOf(action).name) {
                AuthAction.LOGIN.name -> LoginPage()
                AuthAction.REGISTER.name -> RegisterPage()
            }
        }
    }
}