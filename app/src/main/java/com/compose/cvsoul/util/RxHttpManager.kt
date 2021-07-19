package com.compose.cvsoul.util

import rxhttp.wrapper.annotation.Converter
import rxhttp.wrapper.converter.ProtoConverter

/**
 * RxHttp自定义Converter
 */
object RxHttpManager {
    @Converter(name = "ProtoBufConverter") //指定Converter名称
    @JvmField
    var protobufConverter = ProtoConverter()
}