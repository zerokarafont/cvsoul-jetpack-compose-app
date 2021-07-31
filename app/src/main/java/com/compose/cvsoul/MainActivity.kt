package com.compose.cvsoul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.cvsoul.repository.model.MessageEvent
import com.compose.cvsoul.repository.model.MessageType
import com.compose.cvsoul.ui.screen.AuthScreen
import com.compose.cvsoul.ui.screen.MainScreen
import com.compose.cvsoul.ui.screen.SearchScreen
import com.compose.cvsoul.ui.theme.CVSoulTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CVSoulTheme {
                    navController = rememberNavController()

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        when(event.type) {
            MessageType.UNAUTHORIZED -> navController.navigate(route = "auth")
        }
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    companion object {
        init {
            System.loadLibrary("spec")
        }
    }
}