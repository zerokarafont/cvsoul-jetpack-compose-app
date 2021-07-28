package com.compose.cvsoul.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.compose.cvsoul.repository.model.ProfileModel
import com.compose.cvsoul.repository.service.UserService
import com.compose.cvsoul.util.crypto.removeToken
import com.compose.cvsoul.util.toast

class ProfileViewModel: ViewModel() {
    private val _user = MutableLiveData<ProfileModel?>(null)

    val user: LiveData<ProfileModel?> = _user

    suspend fun fetchProfile() {
        rxLifeScope.launch({
            val data = UserService.fetchProfile()
            _user.value = data
        }, {
            toast(it.message)
        })
    }

    fun logout() {
        removeToken()
        _user.value = null
    }
}