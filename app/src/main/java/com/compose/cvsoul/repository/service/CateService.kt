package com.compose.cvsoul.repository.service

import com.compose.cvsoul.repository.model.CateModel
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

object CateService {
    /**
     * 获取所有分类
     */
    suspend fun fetchAllCates(): List<CateModel> {
        return RxHttp
            .getEncrypt("/cate/all")
            .setDecoderEnabled(false)
            .toResponse<List<CateModel>>()
            .await()
    }
}