package com.compose.cvsoul.util

import rxhttp.wrapper.annotation.DefaultDomain
import rxhttp.wrapper.annotation.Domain

/**
 * 配置RxHttp域名
 */
object RxHttpUrl {
    @DefaultDomain
    @JvmField
    val baseUrl = "http://192.168.0.102:9000"
}