package com.compose.cvsoul.repository.service

import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.repository.model.QuoteAlbumPlaylistModel
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

object QuoteService {

    /**
     * 获取语录封面分页数据
     * @param page 页码
     * @param pageSize 分页大小
     * @param title 标题
     */
    suspend fun fetchQuoteAlbumPaginationList(page: Int, pageSize: Int, title: String? = null): List<QuoteAlbumDisplayModel?> {
        return RxHttp
            .getEncrypt("/quote/playlist/display")
            .addQuery("page", page)
            .addQuery("pageSize", pageSize)
            .addQuery("title", title)
            .toResponse<List<QuoteAlbumDisplayModel?>>()
            .await()
    }

    /**
     * 获取语录详情列表
     * @param _id 专辑id
     */
    suspend fun fetchQuoteAlbumDetail(_id: String): QuoteAlbumPlaylistModel {
        return RxHttp
            .getEncrypt("/quote/playlist/detail")
            .addQuery("_id", _id)
            .toResponse<QuoteAlbumPlaylistModel>()
            .await()
    }
}