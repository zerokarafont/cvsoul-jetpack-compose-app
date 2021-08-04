package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.cvsoul.ui.component.CustomBottomNavigation
import com.compose.cvsoul.ui.component.CustomTopAppBar
import com.compose.cvsoul.ui.component.Profile
import com.compose.cvsoul.viewmodel.QuoteViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun MainScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val mainNavController = rememberNavController()

    fun handleOpenDrawer() {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = true,
        backgroundColor = Color(0xFFEEEEEE),
        drawerContent = { Profile(navController = navController) },
        topBar = { CustomTopAppBar(navController = navController, onExpand = { handleOpenDrawer() }) },
        bottomBar = { CustomBottomNavigation(navController = mainNavController) },
    ) {
        val quoteViewModel = viewModel<QuoteViewModel>()
        val chatroomListState = rememberLazyListState()
        val quoteListState = rememberLazyListState()
        NavHost(navController = mainNavController, startDestination = "chatroom") {
                composable(route = "chatroom") {
                    ChatRoomScreen(navController = navController, listState = chatroomListState)
                }
                composable(route = "quote") {
                    QuoteScreen(navController = navController, viewModel = quoteViewModel, listState = quoteListState )
                }
                composable(route = "person") {
                    MessageScreen(navController = navController)
                }
        }
    }
}