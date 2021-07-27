package com.compose.cvsoul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.cvsoul.ui.screen.AuthScreen
import com.compose.cvsoul.ui.screen.MainScreen
import com.compose.cvsoul.ui.screen.SearchScreen
import com.compose.cvsoul.ui.theme.CVSoulTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CVSoulTheme {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "main", route = "root") {
                        composable(route = "main") {
                            MainScreen(navController = navController)
                        }
                        composable(route = "search") { SearchScreen(navController = navController) }
                        composable(route = "auth") { AuthScreen(navController = navController) }
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