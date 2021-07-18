package com.compose.cvsoul.util.crypto

import android.content.Context
import com.compose.cvsoul.CVSoulApplication

/**
 * 获取缓存的token
 */
fun getToken(): String? {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    return prefs.getString("token", "")
}

/**
 * 缓存token
 */
fun setToken(token: String) {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    val editor = prefs.edit()

    editor.putString("token", token)
    editor.apply()
}

/**
 * 删除token缓存
 */
fun removeToken() {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    val editor = prefs.edit()

    editor.remove("token")
    editor.apply()
}