package com.compose.cvsoul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.cvsoul.repository.model.MessageEvent
import com.compose.cvsoul.repository.model.MessageType
import com.compose.cvsoul.ui.screen.*
import com.compose.cvsoul.ui.theme.CVSoulTheme
import com.compose.cvsoul.viewmodel.QuoteDetailViewModel
import com.compose.cvsoul.viewmodel.QuoteListByTagViewModel
import com.compose.cvsoul.viewmodel.SearchViewModel
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

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CVSoulTheme {
                    navController = rememberNavController()

                    var searchListStateGroup = emptyList<LazyListState>()
                    repeat(3) {
                       searchListStateGroup = searchListStateGroup.plus(rememberLazyListState())
                    }
                    val searchViewModel = SearchViewModel(searchListStateGroup)

                    val quoteByTagListState = rememberLazyListState()
                    val quoteListByTagViewModel = QuoteListByTagViewModel(quoteByTagListState)

                    val quoteDetailViewModel = QuoteDetailViewModel()
                    NavHost(navController = navController, startDestination = "main", route = "root") {
                        composable(route = "main") {
                            MainScreen(navController = navController)
                        }
                        composable(route = "search") { SearchScreen(navController = navController, viewModel = searchViewModel) }
                        composable(route = "auth") { AuthScreen(navController = navController) }
                        composable(route = "quote_list_by_tag/{tagName}/{tagId}") { navBackStackEntry ->
                            QuoteListByTagScreen(
                                navController = navController,
                                viewModel = quoteListByTagViewModel,
                                tagName = navBackStackEntry.arguments?.getString("tagName")!!,
                                tagId = navBackStackEntry.arguments?.getString("tagId")!!
                            )
                        }
                        composable(route = "quote_detail/{_id}") { navBackStackEntry ->
                            QuoteDetailScreen(
                                navController = navController,
                                viewModel = quoteDetailViewModel,
                                _id = navBackStackEntry.arguments?.getString("_id")!!
                            )
                        }
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