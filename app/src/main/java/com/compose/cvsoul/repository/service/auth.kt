package com.compose.cvsoul.repository.service

import androidx.lifecycle.liveData
import com.compose.cvsoul.util.crypto.getRawBase64Key
import com.soywiz.krypto.AES
import com.soywiz.krypto.Padding
import com.soywiz.krypto.encoding.Base64
import com.soywiz.krypto.encoding.base64
import kotlinx.coroutines.Dispatchers
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * 注册
 * @param username 用户名
 * @param password 密码
 * @param code 邀请码
 */
fun register(username: String, password: String, code: String) = liveData<String>(Dispatchers.IO) {
    val key = getRawBase64Key()
    val encryptPass = AES.encryptAes128Cbc(password.toByteArray(), Base64.decode(key!!), Padding.PKCS7Padding).base64
    RxHttp
        .postEncryptJson("/auth/register")
        .add("username", username)
        .add("password", encryptPass)
        .add("code", code)
        .toResponse<String>()
        .await()
}

/**
 * 登录
 * @param username 用户名
 * @param password 密码
 * @return token
 */
fun login(username: String, password: String) = liveData<String>(Dispatchers.IO) {
    val key = getRawBase64Key()
    val encryptPass = AES.encryptAes128Cbc(password.toByteArray(), Base64.decode(key!!), Padding.PKCS7Padding).base64
    RxHttp
        .postEncryptJson("/auth/login")
        .add("username", username)
        .add("password", encryptPass)
        .toResponse<String>()
        .await()
}