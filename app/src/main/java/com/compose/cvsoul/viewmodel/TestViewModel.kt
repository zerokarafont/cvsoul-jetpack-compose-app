package com.compose.cvsoul.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.compose.cvsoul.util.Response
import com.compose.cvsoul.util.crypto.getRawBase64Key
import com.google.gson.reflect.TypeToken
import com.rxlife.coroutine.RxLifeScope
import com.soywiz.krypto.AES
import com.soywiz.krypto.Padding
import com.soywiz.krypto.encoding.Base64
import com.soywiz.krypto.encoding.base64
import io.vertx.core.json.Json
import rxhttp.map
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse
import rxhttp.wrapper.utils.GsonUtil

class TestViewModel : ViewModel() {
    suspend fun testGet() {
        rxLifeScope.launch {
            val resp = RxHttp
                .getEncrypt("/auth/test")
                .setDecoderEnabled(true)
                .addQuery("age", 1)
                .toResponse<String>()
                .map { GsonUtil.fromJson<List<String>>(it, List::class.java) }
                .await()
            Log.d("debug", "getData: ${resp[0]}")
        }
    }

    suspend fun testLogin() {
        val key = getRawBase64Key()
        val password = AES.encryptAes128Cbc("123456".toByteArray(), Base64.decode(key!!), Padding.PKCS7Padding).base64

        rxLifeScope.launch {
            val resp = RxHttp
                .postEncryptJson("/auth/login")
                .add("username", "admin")
                .add("password", password)
                .toResponse<String>()
                .await()
            Log.d("debug", "token: $resp")
        }
    }
}