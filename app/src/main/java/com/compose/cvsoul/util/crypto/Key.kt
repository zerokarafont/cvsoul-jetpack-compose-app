package com.compose.cvsoul.util.crypto

import android.content.Context
import com.compose.cvsoul.CVSoulApplication
import com.compose.cvsoul.util.RSA
import com.soywiz.krypto.AES
import com.soywiz.krypto.MD5
import com.soywiz.krypto.encoding.Base64
import java.nio.charset.Charset
import java.security.KeyPairGenerator
import java.security.SecureRandom
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

const val PUBLIC_KEY =
    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrWFdcvgt+q4NOO2kcXH7zu9Ermd7/dNbgzXWbXOjkuMTCVKgkrsWK4tsIFuuSVbteW8h64WnTR27jAqbQQUmn60Hxi2/cwvHm9XtbBgizw4okdND5w5cYYtcga2FIboe6zDOUNqIl10PPdIS4ub0939htyTgvyBEjuDzbWaDlQwIDAQAB"

fun generateAppKey(isRaw: Boolean): String {
    val prefs = CVSoulApplication.context.getSharedPreferences("key", Context.MODE_PRIVATE)
    // 获取已存在的aesKey
    var key = prefs.getString("key", "")
    if (key.isNullOrEmpty()) {
        val editor = prefs.edit()
        // 如果aeskey不存在或者为空字符串， 则生成一个新的key
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(128, SecureRandom())
        val newKey = String(keyGen.generateKey().encoded)

        // 生成随机特征码
        val randomSpecCode = JNI.genSpecKey()

        key = newKey + randomSpecCode
        editor.putString("key", newKey)
        editor.apply()
    }

    if (isRaw) {
        return key
    }

    return Base64.encode(RSA.encryptMessage(key, PUBLIC_KEY).toByteArray(Charset.defaultCharset()))
}

fun generateSign(requestData: String, timestamp: String, nonce: String): String {
    val appKey = generateAppKey(true)
    val toBeDigest = "$requestData + $timestamp + $nonce + $appKey".toByteArray()

    return MD5.digest(toBeDigest).base64
}