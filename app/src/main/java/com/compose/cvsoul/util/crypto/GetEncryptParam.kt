package com.compose.cvsoul.util.crypto

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

        val rawBase64Key = getRawBase64KeyFromCacheOrOtherwiseNew()
        val encryptAppKey = generateAppKey(rawBase64Key!!)
        val sign = generateSign(params, timestamp!!, nonce!!, rawBase64Key)

        addHeader("sign", sign)
        addHeader("appKey", encryptAppKey)

        return if (params.isEmpty()) simpleUrl.toHttpUrl() else "$simpleUrl?$params".toHttpUrl()
    }
}