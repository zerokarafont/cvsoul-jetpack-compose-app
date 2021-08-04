package com.compose.cvsoul.ui.layout

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun <T : Any> SwipeRefreshLayout(data: LazyPagingItems<T>?, content: @Composable (data: LazyPagingItems<T>?) -> Unit) {
    val rememberSwipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        state = rememberSwipeRefreshState,
        onRefresh = {
            data?.refresh()
        },
    ) {
        rememberSwipeRefreshState.isRefreshing = data?.loadState?.refresh is LoadState.Loading

        content(data)
    }
}