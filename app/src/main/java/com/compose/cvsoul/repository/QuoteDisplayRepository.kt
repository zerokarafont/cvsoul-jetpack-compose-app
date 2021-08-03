package com.compose.cvsoul.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compose.cvsoul.repository.model.CateModel
import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.repository.service.CateService
import com.compose.cvsoul.repository.service.QuoteService

/**
 * 语录封面集显示仓库
 */
object QuoteDisplayRepository {

    suspend fun fetchAllCates(): List<CateModel> {
        return CateService.fetchAllCates()
    }

    fun fetchQuoteAlbumPaginationList(cateId: String, title: String? = null) = Pager(config = PagingConfig(18)) {
        DataPagingSource(QuoteService, cateId, title)
    }.flow

    class DataPagingSource(private val quoteService: QuoteService, private val cateId: String,private val title: String? = null) : PagingSource<Int, QuoteAlbumDisplayModel>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteAlbumDisplayModel> {
            return try {
                val page = params.key ?: 1
                val pageSize = params.loadSize
                val resp = quoteService.fetchQuoteAlbumPaginationList(page, pageSize, cateId, title)
                val items = resp.data
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (items.isNotEmpty()) page + 1 else null
                LoadResult.Page(items, prevKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, QuoteAlbumDisplayModel>): Int? = null

    }

}