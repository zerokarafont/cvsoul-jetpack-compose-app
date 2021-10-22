package com.compose.cvsoul.repository

import androidx.paging.*
import com.compose.cvsoul.repository.model.ChatroomDisplayModel
import com.compose.cvsoul.repository.service.ChatroomService
import kotlinx.coroutines.flow.Flow

/**
 * 聊天室仓库
 */
object ChatroomRepository {
    fun fetchChatroomPaginationList(title: String? = null): Flow<PagingData<ChatroomDisplayModel>> {
        return Pager(
            config = PagingConfig(pageSize = 18, initialLoadSize = 18),
            pagingSourceFactory = { DataPagingSource(ChatroomService, title) }
        ).flow
    }

    class DataPagingSource(private val chatroomService: ChatroomService, private val title: String? = null) : PagingSource<Int, ChatroomDisplayModel>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatroomDisplayModel> {
            return try {
                val page = params.key ?: 1
                val pageSize = params.loadSize
                val resp = chatroomService.fetchChatroomPaginationList(page, pageSize, title)
                val items = resp.data
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (items.isNotEmpty()) page + 1 else null
                LoadResult.Page(items, prevKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, ChatroomDisplayModel>): Int? = null

    }
}