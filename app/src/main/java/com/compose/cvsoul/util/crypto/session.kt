package com.compose.cvsoul.util.crypto

import android.content.Context
import com.compose.cvsoul.CVSoulApplication

/**
 * 维护ssl加密会话
 */

/**
 * 获取缓存的sessionId
 */
fun getSessionId(): String? {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    return prefs.getString("sessionId", "")
}

/**
 * 缓存sessionId
 */
fun setSessionId(sessionId: String) {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    val editor = prefs.edit()

    editor.putString("sessionId", sessionId)
    editor.apply()
}

/**
 * 删除sessionId缓存
 */
fun removeSessionId() {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    val editor = prefs.edit()

    editor.remove("sessionId")
    editor.apply()
}