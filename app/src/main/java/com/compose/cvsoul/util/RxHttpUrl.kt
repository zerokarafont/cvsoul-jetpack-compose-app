package com.compose.cvsoul.util

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * 配置RxHttp域名
 */
object RxHttpUrl {
    @DefaultDomain
    @JvmField
    val baseUrl = "http://192.168.0.104:9000/app"
}