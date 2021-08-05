package com.compose.cvsoul.repository.model

import androidx.compose.foundation.lazy.LazyListState
import androidx.paging.compose.LazyPagingItems

data class LazyPagingData<T : Any>(val items: LazyPagingItems<T>, val listState: LazyListState)
