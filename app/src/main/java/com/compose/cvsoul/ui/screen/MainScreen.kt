package com.compose.cvsoul.ui.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.compose.cvsoul.ui.component.CustomBottomNavigation
import com.compose.cvsoul.ui.component.CustomTopAppBar
import com.compose.cvsoul.viewmodel.TestViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

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
        bottomBar = { CustomBottomNavigation(navController = navController) }
    ) {
        NavHost(navController = navController, startDestination = "main") {
            navigation(startDestination = "home", route = "main") {
                composable(route = "home") {
                    HomeScreen(navController = navController)
                }
                composable(route = "person") {
                    MessageScreen(navController = navController)
                }
            }
        }
    }
}