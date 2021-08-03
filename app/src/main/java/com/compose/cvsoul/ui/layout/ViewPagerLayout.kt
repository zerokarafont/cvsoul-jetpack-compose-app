package com.compose.cvsoul.ui.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun ViewPagerLayout(tabs: List<CateModel>? = null, content: @Composable (page: Int) -> Unit) {
    val pages = tabs

    if (!pages.isNullOrEmpty()) {
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
            content(page)
        }
    }

}