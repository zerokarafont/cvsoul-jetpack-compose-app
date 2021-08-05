package com.compose.cvsoul.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.compose.cvsoul.repository.model.ChatroomDisplayModel
import com.compose.cvsoul.ui.layout.LazyGridLayout

@ExperimentalFoundationApi
@Composable
fun ChatroomGrid(data: LazyPagingItems<ChatroomDisplayModel>?, listState: LazyListState?) {
    LazyGridLayout(data = data, listState = listState) {
        ChatroomGridItem(item = it)
    }
}

@Composable
fun ChatroomGridItem(item: ChatroomDisplayModel) {
    Card {
        Text(text = item.title)
    }
}