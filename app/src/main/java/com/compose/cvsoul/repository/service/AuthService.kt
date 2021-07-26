package com.compose.cvsoul.repository.service

import com.compose.cvsoul.util.crypto.getRawBase64Key
import com.compose.cvsoul.util.crypto.getRawBase64KeyFromCacheOrOtherwiseNew
import com.soywiz.krypto.AES
import com.soywiz.krypto.Padding
import com.soywiz.krypto.encoding.Base64
import com.soywiz.krypto.encoding.base64
import rxhttp.wrapper.cahce.CacheMode
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

object AuthService {
    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param confirmPass 确认密码
     * @param code 邀请码
     */
    suspend fun register(username: String, password: String, confirmPass: String, code: String): String {
        val key = getRawBase64Key()
        val encryptPass = AES.encryptAes128Cbc(password.toByteArray(), Base64.decode(key!!), Padding.PKCS7Padding).base64
        val encryptConfirmPass = AES.encryptAes128Cbc(confirmPass.toByteArray(), Base64.decode(key!!), Padding.PKCS7Padding).base64
        return RxHttp
            .postEncryptJson("/auth/register")
            .setCacheMode(CacheMode.ONLY_NETWORK)
            .add("username", username)
            .add("password", encryptPass)
            .add("confirmPass", encryptConfirmPass)
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
    suspend fun login(username: String, password: String): String {
        val key = getRawBase64KeyFromCacheOrOtherwiseNew()
        val encryptPass = AES.encryptAes128Cbc(password.toByteArray(), Base64.decode(key!!), Padding.PKCS7Padding).base64
        return RxHttp
            .postEncryptJson("/auth/login")
            .setCacheMode(CacheMode.ONLY_NETWORK)
            .add("username", username)
            .add("password", encryptPass)
            .toResponse<String>()
            .await()
    }
}