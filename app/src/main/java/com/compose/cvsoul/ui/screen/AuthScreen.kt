package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.compose.cvsoul.ui.component.LoadingModal
import com.compose.cvsoul.util.toast
import com.compose.cvsoul.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

enum class AuthAction(value: String) {
    LOGIN("login"),
    REGISTER("register")
}

@Composable
fun AuthScreen(navController: NavController) {
    val viewModel      = viewModel<AuthViewModel>()
    val isLoading         by viewModel.isLoading.observeAsState(false)
    val isLoginSuccess    by viewModel.isLoginSuccess.observeAsState(false)
    val isRegisterSuccess by viewModel.isRegisterSuccess.observeAsState(false)

    var action      by remember { mutableStateOf("LOGIN") }
    var username    by remember { mutableStateOf("") }
    var password    by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var code        by remember { mutableStateOf("") }
    
    val scope = rememberCoroutineScope()

    DisposableEffect(isLoginSuccess) {
        if (isLoginSuccess) {
            navController.navigate(route = "main")
        }
        onDispose {}
    }

    DisposableEffect(isRegisterSuccess) {
        if (isRegisterSuccess) {
            action = "LOGIN"
        }
        onDispose {}
    }

    val isFormValid by remember(username, password, confirmPass, code) {
        derivedStateOf {
            when(AuthAction.valueOf(action).name) {
                AuthAction.LOGIN.name -> username.isNotEmpty() && password.isNotEmpty()
                AuthAction.REGISTER.name -> username.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty() && code.isNotEmpty()
                else -> false
            }
        }
    }

    suspend fun handleRegister() {
        if (password != confirmPass) {
            toast("输入密码不一致")
            return
        }
        viewModel.register(username, password, confirmPass, code)
    }

    suspend fun handleLogin() {
        viewModel.login(username, password)
    }


    @Composable
    fun RegisterPage() {
        Row {
            Text(text = "账号注册")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = username,
                onValueChange = { username = it.trim() },
                label = { Text(text = "用户名") }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = password,
                onValueChange = { password = it.trim() },
                label = { Text(text = "密码") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = confirmPass,
                onValueChange = { confirmPass = it.trim() },
                label = { Text(text = "确认密码") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            OutlinedTextField(
                value = code,
                onValueChange = { code = it.trim() },
                label = { Text(text = "邀请码") },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Button(
                enabled = isFormValid && !isLoading,
                onClick = { scope.launch { handleRegister() } }
            ) {
                Text(text = "注册")
            }
            OutlinedButton(
                onClick = { action = "LOGIN" },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent)
            ) {
                Text(text = "登录")
            }
        }
    }

    @Composable
    fun LoginPage() {
        Row {
            Text(text = "账号登录")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = username,
                onValueChange = { username = it.trim() },
                label = { Text(text = "用户名") }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            TextField(
                value = password,
                onValueChange = { password = it.trim() },
                label = { Text(text = "密码") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { action = "REGISTER" },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent)
            ) {
                Text(text = "注册")
            }
            Button(
                enabled = isFormValid && !isLoading,
                onClick = { scope.launch { handleLogin() } }
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
        Row(modifier = Modifier.align(Alignment.TopEnd)) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = null)
            }
        }
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            when(AuthAction.valueOf(action).name) {
                AuthAction.LOGIN.name -> LoginPage()
                AuthAction.REGISTER.name -> RegisterPage()
            }
        }
    }
    if (isLoading) {
       LoadingModal()
    }
}