package com.compose.cvsoul.repository.service

import com.compose.cvsoul.repository.model.TagModel
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

object TagService {
    /**
     * 获取所有分类
     */
    suspend fun fetchAllTags(): List<TagModel> {
        return RxHttp
            .getEncrypt("/tag/all")
            .setDecoderEnabled(false)
            .toResponse<List<TagModel>>()
            .await()
    }
}