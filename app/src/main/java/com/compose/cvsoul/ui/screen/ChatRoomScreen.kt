package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.paging.Pager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun ChatRoomScreen(navController: NavController) {
    val pages = remember {
        listOf(
            mapOf("name" to "default", "title" to "发现")
        )
    }
    val pagerState = rememberPagerState(pageCount = pages.size)
    
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        modifier = Modifier
            .requiredHeight(30.dp)
            .zIndex(1f)
    ) {
        pages.forEachIndexed { index, item ->
            Tab(
                text = { Text(item["title"]!!) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .wrapContentSize(align = Alignment.Center, unbounded = false)
            .padding(top = 35.dp)
    ) { page ->

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