package com.compose.cvsoul.util

import com.compose.cvsoul.util.crypto.generateRawBase64Key
import com.compose.cvsoul.util.crypto.getSessionId
import com.compose.cvsoul.util.crypto.setRawBase64Key
import com.compose.cvsoul.util.crypto.setSessionId
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.TypeParser
import rxhttp.wrapper.utils.convertTo
import java.io.IOException
import java.lang.reflect.Type

data class Response<T>(val statusCode: Int = 0, val msg: String? = null, var data: T? = null)
data class PageList<T>(val curPage: Int = 0, val pageCount: Int = 0, val total: Int = 0, val list: List<T>? = null)

@Parser(name = "Response", wrappers = [List::class, PageList::class])
open class ResponseParser<T> : TypeParser<T> {
    protected constructor() : super()

    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val sessionId = response.header("sessionId")
        if (sessionId.isNullOrEmpty()) {
            throw Exception("sessionId不能为空")
        }

        if (getSessionId().isNullOrEmpty()) {
            // 如果是第一次建立会话, 直接存入新的sessionId
            setSessionId(sessionId)
        }else if (sessionId == "expired") {
            // 客户端识别到 expired 更新密钥, 告诉服务器密钥已更新, 需要服务器生成新的sessionId
            setSessionId("update")

            val newKey = generateRawBase64Key()
            // 覆盖掉原来的key
            setRawBase64Key(newKey)
        } else if (getSessionId() != sessionId) {
            // 收到新的sessionId, 覆写存入新的sessionId
            setSessionId(sessionId)
        }

        val data: Response<T> = response.convertTo(Response::class, *types)
        var t = data.data
        if (t == null && types[0] === String::class.java) {
            /*
             * 考虑到有些时候服务端会返回：{"errorCode":0,"errorMsg":"关注成功"}  类似没有data的数据
             * 此时code正确，但是data字段为空，直接返回data的话，会报空指针错误，
             * 所以，判断泛型为String类型时，重新赋值，并确保赋值不为null
             */
            @Suppress("UNCHECKED_CAST")
            t = data.msg as T
        }
        if ((data.statusCode != 200 && data.statusCode != 401) || t == null) {
            //code不等于200，说明数据不正确，抛出异常
            throw ParseException(data.statusCode.toString(), data.msg, response)
        }

        return t
    }
}
