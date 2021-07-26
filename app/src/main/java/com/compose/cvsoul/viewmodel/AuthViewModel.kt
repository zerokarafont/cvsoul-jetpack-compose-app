package com.compose.cvsoul.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.compose.cvsoul.repository.service.AuthService
import com.compose.cvsoul.util.crypto.setToken
import com.compose.cvsoul.util.toast

class AuthViewModel: ViewModel() {
    val isLoading      = MutableLiveData(false)
    val isLoginSuccess = MutableLiveData(false)

    suspend fun login(username: String, password: String) {
        rxLifeScope.launch({
            val token = AuthService.login(username, password)
            setToken(token)
            toast("登录成功")
            isLoginSuccess.value = true
        }, {
            toast(it.message)
        }, {
            isLoading.value = true
        }, {
            isLoading.value = false
        })
    }

    suspend fun register(username: String, password: String, confirmPass: String, code: String) {
        rxLifeScope.launch({
            AuthService.register(username, password, confirmPass, code)
            toast("注册成功,请登录")
        }, {
            toast(it.message)
        }, {
            isLoading.value = true
        }, {
            isLoading.value = false
        })
    }
}