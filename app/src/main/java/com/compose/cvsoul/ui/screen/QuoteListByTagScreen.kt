package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.cvsoul.ui.component.QuoteList
import com.compose.cvsoul.viewmodel.QuoteListByTagViewModel

@Composable
fun QuoteListByTagScreen(navController: NavController, viewModel: QuoteListByTagViewModel, tagName: String, tagId: String) {
    val list by viewModel.list.observeAsState()
    val listState = viewModel.listState

    LaunchedEffect(tagId) {
        viewModel.fetchQuoteAlbumPaginationList(tagId)
    }

    Box {
       Column {
           Row(
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier
                   .requiredHeight(56.dp)
                   .padding(vertical = 10.dp)
           ) {
               IconButton(onClick = { navController.popBackStack() }) {
                   Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "返回")
               }
               Text(text = tagName)
           }
           QuoteList(data = list?.collectAsLazyPagingItems(), listState = listState, navigateTo = { _id -> navController.navigate(route = "quote_detail/$_id") })
       }
    }

}