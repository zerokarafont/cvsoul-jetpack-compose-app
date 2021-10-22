package com.compose.cvsoul.repository.service

import com.compose.cvsoul.repository.model.PaginationList
import com.compose.cvsoul.repository.model.VoiceModel
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

object VoiceService {
    /**
     * 获取音频分页数据
     * @param page 页码
     * @param pageSize 分页大小
     * @param content 内容
     */
    suspend fun fetchVoicePaginationList(page: Int, pageSize: Int, content: String? = null): PaginationList<VoiceModel> {
        return RxHttp
            .getEncrypt("/voice/playlist")
            .addQuery("page", page)
            .addQuery("pageSize", pageSize)
            .addQuery("content", content)
            .toResponse<PaginationList<VoiceModel>>()
            .await()
    }
}