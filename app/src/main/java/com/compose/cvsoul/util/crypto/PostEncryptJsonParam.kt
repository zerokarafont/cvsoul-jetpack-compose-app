package com.compose.cvsoul.util.crypto

import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import rxhttp.wrapper.annotation.Param
import rxhttp.wrapper.param.JsonParam
import rxhttp.wrapper.param.Method
import rxhttp.wrapper.utils.GsonUtil

@Param(methodName = "postEncryptJson")
class PostEncryptJsonParam(url: String) : JsonParam(url, Method.POST) {

    private var MEDIA_TYPE_JSON: MediaType = "application/json; charset=utf-8".toMediaType()

    override fun getRequestBody(): RequestBody {
        val json = if (bodyParam == null) "" else GsonUtil.toJson(bodyParam)

        val timestamp = headers["timestamp"]
        val nonce = headers["nonce"]

        // 先从缓存中获取key, 如果不存在则生成一个新的key
        if (getRawBase64Key().isNullOrEmpty()) {
            val initKey = generateRawBase64Key()
            setRawBase64Key(initKey)
        }
        val rawBase64Key = getRawBase64Key()
        val encryptAppKey = generateAppKey(rawBase64Key!!)
        val sign = generateSign(json, timestamp!!, nonce!!, rawBase64Key)

        addHeader("sign", sign)
        addHeader("appKey", encryptAppKey)

        return json.toRequestBody(MEDIA_TYPE_JSON)
    }
}