package com.compose.cvsoul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.compose.cvsoul.ui.component.CustomBottomNavigation
import com.compose.cvsoul.ui.component.CustomTopAppBar
import com.compose.cvsoul.ui.component.Search
import com.compose.cvsoul.ui.screen.HomeScreen
import com.compose.cvsoul.ui.screen.MainScreen
import com.compose.cvsoul.ui.screen.MessageScreen
import com.compose.cvsoul.ui.screen.SearchScreen
import com.compose.cvsoul.ui.theme.CVSoulTheme
import com.compose.cvsoul.viewmodel.TestViewModel
import kotlinx.coroutines.launch

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