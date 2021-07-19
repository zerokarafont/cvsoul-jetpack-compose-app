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
import com.compose.cvsoul.ui.component.CustomBottomNavigation
import com.compose.cvsoul.ui.screen.HomeScreen
import com.compose.cvsoul.ui.screen.MessageScreen
import com.compose.cvsoul.ui.theme.CVSoulTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CVSoulTheme {
                val navController = rememberNavController()
                Scaffold(
                    drawerContent = {
                        Text(text = "测试哦")
                    },
                    topBar = {
                        Text(text = "Top")
                    },
                    bottomBar = { CustomBottomNavigation(navController = navController) }
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "home") { HomeScreen(navController = navController) }
                        composable(route = "person") { MessageScreen(navController = navController) }
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