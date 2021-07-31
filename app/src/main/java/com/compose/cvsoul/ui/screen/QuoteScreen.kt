package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.compose.cvsoul.ui.layout.PagerLayout
import com.compose.cvsoul.viewmodel.QuoteViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun QuoteScreen(navController: NavController) {
    val viewModel = viewModel<QuoteViewModel>()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val cates     by viewModel.cates.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAllCates()
    }

    PagerLayout(tabs = cates) { page ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(false),
            onRefresh = {  },
        ) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                contentPadding = PaddingValues(5.dp),
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                items(29) { index ->
                    Card(modifier = Modifier
                        .requiredSize(122.dp)
                        .padding(bottom = 10.dp)) {
                        Text(text = index.toString())
                    }
                }
            }
        }
    }
}