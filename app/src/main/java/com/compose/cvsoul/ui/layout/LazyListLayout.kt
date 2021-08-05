package com.compose.cvsoul.ui.layout

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> LazyListLayout(data: LazyPagingItems<T>?, listState: LazyListState?, content: @Composable (item: T) -> Unit) {
    data?.let {
        listState?.let { state ->
            LazyColumn(state = state) {
                items(it.itemCount) { index ->
                    val item = it[index]
                    item?.let {
                        content(item = item)
                    }
                }
            }
        }
    }
}