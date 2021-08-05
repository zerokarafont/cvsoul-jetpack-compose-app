package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.cvsoul.ui.component.QuoteGrid
import com.compose.cvsoul.ui.layout.SwipeRefreshLayout
import com.compose.cvsoul.ui.layout.ViewPagerLayout
import com.compose.cvsoul.viewmodel.QuoteViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun QuoteScreen(navController: NavController, viewModel: QuoteViewModel) {
    val cates            by viewModel.cates.observeAsState()
    val pagingData       by viewModel.list.observeAsState()
    val currentCateId    by viewModel.currentCateId.observeAsState("")
    val currentViewPager by viewModel.currentViewPager.observeAsState(0)

    val listState        = viewModel.listState
    val collectAsLazyPagingAlbums = pagingData?.collectAsLazyPagingItems()

    LaunchedEffect(cates) {
        if (cates == null) {
            viewModel.fetchAllCates()
        }
    }

    LaunchedEffect(currentCateId) {
        if (currentCateId.isNotEmpty()) {
            viewModel.fetchQuoteAlbumPaginationList(cateId = currentCateId)
        }
    }

    fun handleTabChange(cateId: String, page: Int) {
        viewModel.changeCurrentCateId(cateId)
        viewModel.changeCurrentViewPager(page)
    }

    ViewPagerLayout(tabs = cates, onTap = { cate, page -> handleTabChange(cate._id, page) }) { page ->
        SwipeRefreshLayout(data = collectAsLazyPagingAlbums) {
            QuoteGrid(data = it, listState = listState)
        }
    }
}