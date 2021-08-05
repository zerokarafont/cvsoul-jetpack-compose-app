package com.compose.cvsoul.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.compose.cvsoul.repository.model.ChatroomDisplayModel
import com.compose.cvsoul.ui.layout.LazyListLayout

@Composable
fun ChatroomList(data: LazyPagingItems<ChatroomDisplayModel>?, listState: LazyListState?) {
    LazyListLayout(data = data, listState = listState) {
        ChatroomListItem(item = it)
    }
}

@Composable
fun ChatroomListItem(item: ChatroomDisplayModel) {
    Card(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()) {
        Column {
            Text(text = item.title)
        }
    }
}