package com.compose.cvsoul.util

import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * 自定义httpclient
 */
fun getDefaultOkHttpClient() : OkHttpClient {
    val dispatcher = Dispatcher()
    dispatcher.maxRequestsPerHost = 10
    dispatcher.maxRequests = 100
    return OkHttpClient
        .Builder()
        .dispatcher(dispatcher)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
//        .connectionPool(ConnectionPool(32,5,TimeUnit.MINUTES))
        .hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true }) //忽略host验证
        .build()
}