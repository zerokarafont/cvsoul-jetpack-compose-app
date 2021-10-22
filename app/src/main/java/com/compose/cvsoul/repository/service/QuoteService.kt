package com.compose.cvsoul.repository.service

import com.compose.cvsoul.repository.model.*
import kotlinx.coroutines.Dispatchers
import rxhttp.awaitResult
import rxhttp.flowOn
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

object QuoteService {

    /**
     * 获取语录封面分页数据
     * @param page 页码
     * @param pageSize 分页大小
     * @param cateId 分类id
     * @param title 标题
     */
    suspend fun fetchQuoteAlbumPaginationList(page: Int, pageSize: Int, cateId: String? = null, title: String? = null): PaginationList<QuoteAlbumDisplayModel> {
        return RxHttp
            .getEncrypt("/quote/playlist/display")
            .addQuery("page", page)
            .addQuery("pageSize", pageSize)
            .addQuery("cateId", cateId)
            .addQuery("title", title)
            .toResponse<PaginationList<QuoteAlbumDisplayModel>>()
            .await()
    }

    /**
     * 获取语录详情列表
     * @param _id 专辑id
     */
    suspend fun fetchQuoteAlbumDetail(_id: String): QuoteAlbumPlaylistModel {
        return RxHttp
            .get("/quote/playlist/detail")
            .addQuery("_id", _id)
            .addHeader("ignore", "true")
            .setDecoderEnabled(false)
            .toResponse<QuoteAlbumPlaylistModel>()
            .await()
    }
}