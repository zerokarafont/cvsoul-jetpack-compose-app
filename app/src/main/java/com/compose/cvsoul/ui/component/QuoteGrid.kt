package com.compose.cvsoul.ui.component

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.ui.layout.LazyGridLayout

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun QuoteGrid(navController: NavController, data: LazyPagingItems<QuoteAlbumDisplayModel>?, listState: LazyListState?) {
    LazyGridLayout(data = data, listState = listState) {
        QuoteGridItem(item = it, onTap = { _id ->
            navController.navigate(route = "quote_detail/$_id")
        })
    }
}

@ExperimentalMaterialApi
@Composable
fun QuoteGridItem(item: QuoteAlbumDisplayModel, onTap: (_id: String) -> Unit) {
    Card(
        onClick = { onTap(item._id) }
    ) {
        Text(text = item.title)
    }
}