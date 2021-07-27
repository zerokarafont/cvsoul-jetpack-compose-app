package com.compose.cvsoul.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.compose.cvsoul.repository.service.AuthService
import com.compose.cvsoul.util.crypto.setToken
import com.compose.cvsoul.util.toast

class AuthViewModel: ViewModel() {
    private val _isLoading         = MutableLiveData(false)
    private val _isLoginSuccess    = MutableLiveData(false)
    private val _isRegisterSuccess = MutableLiveData(false)

    val isLoading:         LiveData<Boolean> = _isLoading
    val isLoginSuccess:    LiveData<Boolean> = _isLoginSuccess
    val isRegisterSuccess: LiveData<Boolean> = _isRegisterSuccess

    suspend fun login(username: String, password: String) {
        rxLifeScope.launch({
            val token = AuthService.login(username, password)
            setToken(token)
            toast("登录成功")
            _isLoginSuccess.value = true
        }, {
            toast(it.message)
        }, {
            _isLoading.value = true
        }, {
            _isLoading.value = false
        })
    }

    suspend fun register(username: String, password: String, confirmPass: String, code: String) {
        rxLifeScope.launch({
            AuthService.register(username, password, confirmPass, code)
            toast("注册成功,请登录")
            _isRegisterSuccess.value = true
        }, {
            toast(it.message)
        }, {
            _isLoading.value = true
        }, {
            _isLoading.value = false
        })
    }
}