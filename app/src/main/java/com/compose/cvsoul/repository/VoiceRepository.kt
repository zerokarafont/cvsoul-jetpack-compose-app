package com.compose.cvsoul.repository

import androidx.paging.*
import com.compose.cvsoul.repository.model.CateModel
import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.repository.model.VoiceModel
import com.compose.cvsoul.repository.service.CateService
import com.compose.cvsoul.repository.service.QuoteService
import com.compose.cvsoul.repository.service.VoiceService
import kotlinx.coroutines.flow.Flow

/**
 * 音频仓库
 */
object VoiceRepository {

    fun fetchVoicePaginationList(content: String? = null): Flow<PagingData<VoiceModel>> {
        return Pager(
            config = PagingConfig(pageSize = 18, initialLoadSize = 18),
            pagingSourceFactory = { DataPagingSource(VoiceService, content) }
        ).flow
    }

    class DataPagingSource(private val voiceService: VoiceService, private val content: String? = null) : PagingSource<Int, VoiceModel>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VoiceModel> {
            return try {
                val page = params.key ?: 1
                val pageSize = params.loadSize
                val resp = voiceService.fetchVoicePaginationList(page, pageSize, content)
                val items = resp.data
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (items.isNotEmpty()) page + 1 else null
                LoadResult.Page(items, prevKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, VoiceModel>): Int? = null

    }

}