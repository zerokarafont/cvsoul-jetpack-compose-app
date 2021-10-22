package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.compose.cvsoul.viewmodel.ChatroomViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun ChatRoomScreen(navController: NavController, viewModel: ChatroomViewModel) {

    val listState = viewModel.listState

    SwipeRefresh(
        state = rememberSwipeRefreshState(false),
        onRefresh = {  },
    ) {
//       ChatroomGrid(data = , listState = )
        LazyColumn(state = listState) {
            repeat(50) { index ->
                item { 
                    Card {
                        Text(text = index.toString())
                    }
                }
            }
        }
    }
}