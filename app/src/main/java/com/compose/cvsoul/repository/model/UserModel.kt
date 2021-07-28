package com.compose.cvsoul.repository.model

data class UserModel(val _id: String, val username: String, val password: String, val avatar: String)

data class ProfileModel(val username: String, val avatar: String)
