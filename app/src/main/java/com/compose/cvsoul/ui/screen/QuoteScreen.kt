package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.cvsoul.ui.layout.ViewPagerLayout
import com.compose.cvsoul.viewmodel.QuoteViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun QuoteScreen(navController: NavController, viewModel: QuoteViewModel, listState: LazyListState) {
    val cates            by viewModel.cates.observeAsState()
    val pagingData       by viewModel.list.observeAsState()
    val currentCateId    by viewModel.currentCateId.observeAsState("")
    val currentViewPager by viewModel.currentViewPager.observeAsState(0)

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

    ViewPagerLayout(tabs = cates, currentPage = currentViewPager, onTap = { cateId, page -> handleTabChange(cateId, page) }) { page ->
        val rememberSwipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
        SwipeRefresh(
            state = rememberSwipeRefreshState,
            onRefresh = {
                collectAsLazyPagingAlbums?.refresh()
            },
        ) {
            rememberSwipeRefreshState.isRefreshing = collectAsLazyPagingAlbums?.loadState?.refresh is LoadState.Loading

            collectAsLazyPagingAlbums?.let {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    contentPadding = PaddingValues(5.dp),
                    state = listState,
                    modifier = Modifier.padding(bottom = 50.dp)
                ) {
                    items(collectAsLazyPagingAlbums.itemCount) { index ->
                        val item = collectAsLazyPagingAlbums[index]
                        item?.let {
                            Card(modifier = Modifier.size(122.dp)) {
                                Text(text = item.title)
                            }
                        }
                    }
                }
            }
        }
    }
}