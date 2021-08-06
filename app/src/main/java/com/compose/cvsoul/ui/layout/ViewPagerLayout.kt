package com.compose.cvsoul.ui.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.compose.cvsoul.repository.model.CateModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun ViewPagerLayout(tabs: List<CateModel>? = null, offscreenLimit: Int = 1, scrollable: Boolean = false, onTap: (cateModel: CateModel, page: Int) -> Unit, content: @Composable (page: Int) -> Unit) {

    tabs?.let {
        val pagerState = rememberPagerState(pageCount = tabs.size, initialOffscreenLimit = offscreenLimit)
        val currentPage = pagerState.currentPage

        val scope = rememberCoroutineScope()

        DisposableEffect(currentPage) {
            onTap(it[currentPage], currentPage)
            onDispose {  }
        }

        @Composable
        fun Tabs() {
            tabs.forEachIndexed { index, cate ->
                Tab(
                    text = { Text(cate.name) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        if (scrollable) {
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
                Tabs()
            }
        } else {
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
                Tabs()
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center, unbounded = false)
                .padding(top = 35.dp),

        ) { page ->
            content(page)
        }
    }

}