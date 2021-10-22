package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.cvsoul.repository.model.*
import com.compose.cvsoul.ui.component.Search
import com.compose.cvsoul.ui.component.SearchHistory
import com.compose.cvsoul.ui.component.SearchResult
import com.compose.cvsoul.ui.component.Tag
import com.compose.cvsoul.util.setHistory
import com.compose.cvsoul.viewmodel.SearchViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel) {
    var searchInput by remember { mutableStateOf("") }
    val tags by viewModel.tags.observeAsState()
    val searchResponse by viewModel.searchResponse.observeAsState(null)

    LaunchedEffect(Unit) {
        if (tags == null) {
            viewModel.fetchAllTags()
        }
    }

    fun handleBack() {
        navController.popBackStack()
        viewModel.clearSearchResponse()
    }

    fun handleSearch(value: String? = null) {
        if (value?.isNotEmpty() == true) {
            searchInput = value
            viewModel.fetchSearchResultPaginationList(value)
            setHistory(value)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFEEEEEE))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .requiredHeight(56.dp)
                    .padding(vertical = 10.dp)
            ) {
                IconButton(onClick = { handleBack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
                Search(
                    value = searchInput,
                    onValueChange = { searchInput = it },
                    onSearch = { searchInput -> handleSearch(searchInput) },
                    onTap = {},
                    initIsFocus = true
                )
                IconButton(onClick = { handleBack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            }

            SearchHistory(onTap = { historyItem -> handleSearch(historyItem) })
            Tag(data = tags,onTap = { (_id, name) -> navController.navigate(route = "quote_list_by_tag/$name/$_id") })
        }

        @Suppress("UNCHECKED_CAST")
        searchResponse?.let {
            val (voiceLazyPagingItems, voiceListState) = it[0] as FlowPagingData<VoiceModel>
            val (quoteLazyPagingItems, quoteListState) = it[1] as FlowPagingData<QuoteAlbumDisplayModel>
            val (chatroomLazyPagingItems, chatroomListState) = it[2] as FlowPagingData<ChatroomDisplayModel>
            val voiceLazyPagingData = LazyPagingData(items = voiceLazyPagingItems.collectAsLazyPagingItems(), listState = voiceListState)
            val quoteLazyPagingData = LazyPagingData(items = quoteLazyPagingItems.collectAsLazyPagingItems(), listState = quoteListState)
            val chatroomLazyPagingData = LazyPagingData(items = chatroomLazyPagingItems.collectAsLazyPagingItems(), listState = chatroomListState)
            Box(modifier = Modifier.padding(top = 56.dp)) {
                SearchResult(
                    navController,
                    voiceLazyPagingData,
                    quoteLazyPagingData,
                    chatroomLazyPagingData
                )
            }
        }
    }
}