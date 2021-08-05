package com.compose.cvsoul.ui.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems

@ExperimentalFoundationApi
@Composable
fun <T : Any> LazyGridLayout(data: LazyPagingItems<T>?, columns: Int = 3, listState: LazyListState?, content: @Composable (item: T) -> Unit) {
    data?.let {
        listState?.let { state ->
            LazyVerticalGrid(
                cells = GridCells.Fixed(columns),
                contentPadding = PaddingValues(5.dp),
                state = state,
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
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