package com.compose.cvsoul.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.compose.cvsoul.repository.model.VoiceModel
import com.compose.cvsoul.ui.layout.LazyListLayout

@Composable
fun VoiceList(data: LazyPagingItems<VoiceModel>?, listState: LazyListState?) {
    LazyListLayout(data = data, listState = listState) {
        VoiceListItem(item = it)
    }
}

@Composable
fun VoiceListItem(item: VoiceModel) {
    Card(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()) {
        Column {
            Text(text = item.text)
        }
    }
}