package com.compose.cvsoul.ui.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.cvsoul.ui.component.CustomBottomNavigation
import com.compose.cvsoul.ui.component.CustomTopAppBar
import kotlinx.coroutines.launch

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
        drawerGesturesEnabled = false,
        backgroundColor = Color(0xFFEEEEEE),
        drawerContent = {
            Text(text = "测试哦")
        },
        topBar = { CustomTopAppBar(navController = navController, onExpand = { handleOpenDrawer() }) },
        bottomBar = { CustomBottomNavigation(navController = mainNavController) }
    ) {
        NavHost(navController = mainNavController, startDestination = "home") {
                composable(route = "home") {
                    HomeScreen(navController = navController)
                }
                composable(route = "person") {
                    MessageScreen(navController = navController)
                }
        }
    }
}