package com.compose.cvsoul.util

import okhttp3.OkHttpClient
import rxhttp.wrapper.ssl.HttpsUtils
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * 自定义httpclient
 */
fun getDefaultOkHttpClient() : OkHttpClient {
    val sslParams = HttpsUtils.getSslSocketFactory()
    return OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .sslSocketFactory(
            sslParams.sSLSocketFactory,
            sslParams.trustManager
        ) //添加信任证书
        .hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true }) //忽略host验证
        .build()
}