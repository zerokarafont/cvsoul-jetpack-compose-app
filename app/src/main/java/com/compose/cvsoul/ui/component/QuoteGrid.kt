package com.compose.cvsoul.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.ui.layout.LazyGridLayout

@ExperimentalFoundationApi
@Composable
fun QuoteGrid(data: LazyPagingItems<QuoteAlbumDisplayModel>?, listState: LazyListState?) {
    LazyGridLayout(data = data, listState = listState) {
        QuoteGridItem(item = it)
    }
}

@Composable
fun QuoteGridItem(item: QuoteAlbumDisplayModel) {
    Card {
        Text(text = item.title)
    }
}