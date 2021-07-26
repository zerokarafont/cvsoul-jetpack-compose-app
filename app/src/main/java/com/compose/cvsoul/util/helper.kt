package com.compose.cvsoul.util

import android.widget.Toast
import com.compose.cvsoul.CVSoulApplication

fun toast(text: String? = "未知错误") {
    Toast.makeText(CVSoulApplication.context, text, Toast.LENGTH_SHORT).show()
}