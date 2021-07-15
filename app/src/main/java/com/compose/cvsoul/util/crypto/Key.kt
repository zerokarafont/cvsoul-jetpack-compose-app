package com.compose.cvsoul.util.crypto

import android.content.Context
import android.util.Log
import com.compose.cvsoul.CVSoulApplication
import com.compose.cvsoul.util.RSA
import com.soywiz.krypto.AES
import com.soywiz.krypto.MD5
import com.soywiz.krypto.encoding.Base64
import com.soywiz.krypto.encoding.ascii
import com.soywiz.krypto.encoding.base64
import io.vertx.core.json.Json
import okio.internal.commonToUtf8String
import java.net.URLEncoder
import java.nio.charset.Charset
import java.security.KeyPairGenerator
import java.security.SecureRandom
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

const val PUBLIC_KEY =
    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrWFdcvgt+q4NOO2kcXH7zu9Ermd7/dNbgzXWbXOjkuMTCVKgkrsWK4tsIFuuSVbteW8h64WnTR27jAqbQQUmn60Hxi2/cwvHm9XtbBgizw4okdND5w5cYYtcga2FIboe6zDOUNqIl10PPdIS4ub0939htyTgvyBEjuDzbWaDlQwIDAQAB"

fun generateAppKey(): Map<String, String> {
    val prefs = CVSoulApplication.context.getSharedPreferences("key", Context.MODE_PRIVATE)
    // 获取已存在的aesKey
    var key = prefs.getString("key", "")
    if (key.isNullOrEmpty()) {
        val editor = prefs.edit()
        // 如果aeskey不存在或者为空字符串， 则生成一个新的key
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(128, SecureRandom())
        val newKey =  keyGen.generateKey().encoded

        key = newKey.base64

        editor.putString("key", key)
        editor.apply()
    }

    val encryptAppKey = Base64.encode(RSA.encryptMessage(key, PUBLIC_KEY).toByteArray(Charset.forName("UTF-8")))

    return mapOf<String, String>(
        "rawBase64Key" to key,
        "encryptAppKey" to encryptAppKey
    )
}

fun generateSign(requestData: String, timestamp: String, nonce: String, rawBase64Key: String): String {

    // 生成随机特征码
    val randomSpecCode = JNI.genSpecKey()

    val toBeDigest = "$requestData + $timestamp + $nonce + $rawBase64Key + $randomSpecCode".toByteArray(Charset.forName("UTF-8"))

    val Hex = MD5.digest(toBeDigest).hexUpper

    return Hex
}