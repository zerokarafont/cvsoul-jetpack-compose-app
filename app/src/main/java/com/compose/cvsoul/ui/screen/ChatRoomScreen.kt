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
import com.compose.cvsoul.repository.model.CateModel
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
            CateModel("1", "发现"),
            CateModel("2", "搞笑"),
            CateModel("3", "运动"),
            CateModel("4", "励志"),
            CateModel("5", "热血"),
            CateModel("6", "战斗"),
            CateModel("7", "竞技"),
            CateModel("8", "校园"),
        )
    }
    val pagerState = rememberPagerState(pageCount = pages.size)
    
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        edgePadding = 0.dp,
        modifier = Modifier
            .requiredHeight(30.dp)
            .zIndex(1f)
    ) {
        pages.forEachIndexed { index, cate ->
            Tab(
                text = { Text(cate.name) },
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