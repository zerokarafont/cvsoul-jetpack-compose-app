package com.compose.cvsoul.repository.model

import androidx.compose.foundation.lazy.LazyListState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.flow.Flow

data class LazyPagingData<T : Any>(val items: LazyPagingItems<T>, val listState: LazyListState)

data class FlowPagingData<T: Any>(val flow: Flow<PagingData<T>>, val listState: LazyListState)