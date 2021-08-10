package com.compose.cvsoul.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.compose.cvsoul.repository.model.*
import com.compose.cvsoul.ui.layout.SwipeRefreshLayout
import com.compose.cvsoul.ui.layout.ViewPagerLayout
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun SearchResult(
    voiceLazyPagingData: LazyPagingData<VoiceModel>? = null,
    quoteLazyPagingData: LazyPagingData<QuoteAlbumDisplayModel>? = null,
    chatroomLazyPagingData: LazyPagingData<ChatroomDisplayModel>? = null
) {
    val cates = listOf(
        CateModel("voice", "语音"), // 语音列表
        CateModel("quote", "语录"), // 语录集列表
        CateModel("chatroom", "聊天室"), // 聊天室列表
    )
    var currentTab by remember { mutableStateOf("voice") }

    fun handleTabChange(cate: CateModel, page: Int) {
        currentTab = cate._id
    }


        ViewPagerLayout(tabs = cates, offscreenLimit = 2, onTap = { cate, page -> handleTabChange(cate, page)  }) { page ->
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                when(currentTab) {
                    "voice" -> {
                        if (page == 0) {
                            SwipeRefreshLayout(data = voiceLazyPagingData?.items) {
                                VoiceList(data = it, listState = voiceLazyPagingData?.listState)
                            }
                        }
                    }
                    "quote" -> {
                        if (page == 1) {
                            SwipeRefreshLayout(data = quoteLazyPagingData?.items) {
                                QuoteList(data = it, listState = quoteLazyPagingData?.listState)
                            }
                        }
                    }
                    "chatroom" -> {
                        if (page == 2) {
                            SwipeRefreshLayout(data = chatroomLazyPagingData?.items) {
                                ChatroomList(data = it, listState = chatroomLazyPagingData?.listState)
                            }
                        }
                    }
                }
            }
    }
}