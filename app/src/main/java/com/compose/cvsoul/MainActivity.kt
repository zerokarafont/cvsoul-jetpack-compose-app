package com.compose.cvsoul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.cvsoul.ui.screen.HomeScreen
import com.compose.cvsoul.ui.screen.MessageScreen
import com.compose.cvsoul.ui.theme.CVSoulTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CVSoulTheme {
                var selectedItem by remember { mutableStateOf(0) }
                val items = listOf("主页", "消息")
                val navController = rememberNavController()
                Scaffold(
                    drawerContent = {
                        Text(text = "测试哦")
                    },
                    topBar = {
                        Text(text = "Top")
                    },
                    bottomBar = {
                        BottomNavigation {
                            items.forEachIndexed { index, item ->
                                BottomNavigationItem(
                                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                                    label = { Text(item) },
                                    selected = selectedItem == index,
                                    onClick = { selectedItem = index }
                                )
                            }
                        }
                    }
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "home") { HomeScreen(navController = navController) }
                        composable(route = "message") { MessageScreen(navController = navController) }
                    }
                }
            }
        }
    }

    companion object {
        init {
            System.loadLibrary("spec")
        }
    }
}