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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.cvsoul.repository.model.CateModel
import com.compose.cvsoul.ui.layout.PagerLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun QuoteScreen(navController: NavController) {

    val tabs = listOf(
        CateModel("1", "发现"),
        CateModel("2", "搞笑"),
        CateModel("3", "运动"),
        CateModel("4", "励志"),
        CateModel("5", "热血"),
        CateModel("6", "战斗"),
        CateModel("7", "竞技"),
        CateModel("8", "校园"),
    )

    PagerLayout(tabs) { page ->
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