package com.compose.cvsoul.ui.component

import androidx.compose.foundation.clickable
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
import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.ui.layout.LazyListLayout

@Composable
fun QuoteList(data: LazyPagingItems<QuoteAlbumDisplayModel>?, listState: LazyListState?, navigateTo: (_id: String) -> Unit) {
    LazyListLayout(data = data, listState = listState) {
        QuoteListItem(item = it, onTap = { _id -> navigateTo(_id) })
    }
}

@Composable
fun QuoteListItem(item: QuoteAlbumDisplayModel, onTap: (_id: String) -> Unit) {
    Card(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .clickable {
            onTap(item._id)
        }
    ) {
        Column {
            Text(text = item.title)
        }
    }
}