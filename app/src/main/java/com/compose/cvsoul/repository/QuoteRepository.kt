package com.compose.cvsoul.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compose.cvsoul.repository.model.QuoteAlbumModel
import com.compose.cvsoul.repository.service.CateService

object QuoteRepository {


//    class RepoPagingSource(private val cateService: CateService) : PagingSource<Int, QuoteAlbumModel>() {
//
//        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteAlbumModel> {
//            return try {
//                val page = params.key ?: 1
//                val pageSize = params.loadSize
//                val repoResponse = cateService.fetchAllCates()
//                val repoItems = repoResponse.items
//                val prevKey = if (page > 1) page - 1 else null
//                val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
//                LoadResult.Page(repoItems, prevKey, nextKey)
//            } catch (e: Exception) {
//                LoadResult.Error(e)
//            }
//        }
//
//        override fun getRefreshKey(state: PagingState<Int, QuoteAlbumModel>): Int? = null
//
//    }

}