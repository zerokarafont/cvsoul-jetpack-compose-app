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

/**
 * 持久化存储原始key
 */
fun setRawBase64Key(rawBase64Key: String) {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    val editor = prefs.edit()

    editor.putString("key", rawBase64Key)
    editor.apply()
}

/**
 * 获取原始key
 */
fun getRawBase64Key(): String? {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)

    return prefs.getString("key", "")
}

/**
 * 清除原始key
 */
fun removeRawBase64Key() {
    val prefs = CVSoulApplication.context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    val editor = prefs.edit()
    editor.remove("key")
    editor.apply()
}

/**
 * 生成原始key
 */
fun generateRawBase64Key(): String {
    val keyGen = KeyGenerator.getInstance("AES")
    keyGen.init(128, SecureRandom())
    val newKey =  keyGen.generateKey().encoded

    return newKey.base64
}

/**
 * 生成加密key
 */
fun generateAppKey(rawBase64Key: String): String {
    val encryptAppKey = Base64.encode(RSA.encryptMessage(rawBase64Key, PUBLIC_KEY).toByteArray(Charset.forName("UTF-8")))

    return encryptAppKey
}

/**
 * 先从缓存中获取key, 如果不存在则生成一个新的key
 */
fun getRawBase64KeyFromCacheOrOtherwiseNew(): String? {
    if (getRawBase64Key().isNullOrEmpty()) {
        val initKey = generateRawBase64Key()
        setRawBase64Key(initKey)
    }
    return getRawBase64Key()
}

/**
 * 生成请求签名
 */
fun generateSign(requestData: String, timestamp: String, nonce: String, rawBase64Key: String): String {

    // 生成随机特征码
    val randomSpecCode = JNI.genSpecKey()

    val toBeDigest = "$requestData + $timestamp + $nonce + $rawBase64Key + $randomSpecCode".toByteArray(Charset.forName("UTF-8"))

    val Hex = MD5.digest(toBeDigest).hexUpper

    return Hex
}