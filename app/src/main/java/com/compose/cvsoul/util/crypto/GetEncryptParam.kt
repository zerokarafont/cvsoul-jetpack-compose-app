package com.compose.cvsoul.util.crypto

import android.content.res.Configuration
import com.compose.cvsoul.CVSoulApplication
import com.compose.cvsoul.util.RSA
import com.soywiz.krypto.MD5
import com.soywiz.krypto.encoding.Base64
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import rxhttp.wrapper.annotation.Param
import rxhttp.wrapper.param.Method
import rxhttp.wrapper.param.NoBodyParam

@Param(methodName = "getEncrypt")
class GetEncryptParam(url: String) : NoBodyParam(url, Method.GET) {

    override fun getHttpUrl(): HttpUrl {
        var params = ""
        val timestamp = headers["timestamp"]
        val nonce = headers["nonce"]

        for (pair in queryParam) {
            val key = pair.key
            val value = pair.value.toString()
            params += "$key=$value"
        }

        val appKey = generateAppKey(false)
        val sign = generateSign(params, timestamp!!, nonce!!)

        addHeader("sign", sign)
        addHeader("appKey", appKey)

        return if (params.isEmpty()) simpleUrl.toHttpUrl() else "$simpleUrl?$params".toHttpUrl()
    }
}