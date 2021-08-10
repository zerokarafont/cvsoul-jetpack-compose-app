package com.compose.cvsoul.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.compose.cvsoul.CVSoulApplication

fun toast(text: String? = "未知错误") {
    Toast.makeText(CVSoulApplication.context, text, Toast.LENGTH_SHORT).show()
}

fun getHistory(): Set<String> {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    return prefs.getStringSet("history", setOf()) as Set<String>
}

fun setHistory(searchInput: String) {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    var history = prefs.getStringSet("history", setOf())
    val editor = prefs.edit()
    history = history?.plus(searchInput)
    editor.putStringSet("history", history)
    editor.apply()
}

fun clearHistory() {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    val editor = prefs.edit()
    editor.remove("history")
    editor.apply()
}