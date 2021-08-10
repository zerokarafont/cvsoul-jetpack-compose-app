package com.compose.cvsoul.repository

import androidx.paging.*
import com.compose.cvsoul.repository.model.CateModel
import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.repository.service.CateService
import com.compose.cvsoul.repository.service.QuoteService
import kotlinx.coroutines.flow.Flow

/**
 * 语录封面集显示仓库
 */
object QuoteDisplayRepository {

    suspend fun fetchAllCates(): List<CateModel> {
        return CateService.fetchAllCates()
    }

    fun fetchQuoteAlbumPaginationList(cateId: String? = null, title: String? = null): Flow<PagingData<QuoteAlbumDisplayModel>> {
        return Pager(
            config = PagingConfig(pageSize = 18, initialLoadSize = 18),
            pagingSourceFactory = { DataPagingSource(QuoteService, cateId, title) }
        ).flow
    }

    class DataPagingSource(private val quoteService: QuoteService, private val cateId: String? = null,private val title: String? = null) : PagingSource<Int, QuoteAlbumDisplayModel>() {

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