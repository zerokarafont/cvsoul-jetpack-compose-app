package com.compose.cvsoul.ui.component

import android.util.Log
import androidx.compose.runtime.*
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

    ViewPagerLayout(tabs = cates, onTap = { cate, page -> handleTabChange(cate, page)  }) { page ->
        Log.d("debug", "searchResultPage: $page")

        when(currentTab) {
            "voice" -> {
                SwipeRefreshLayout(data = voiceLazyPagingData?.items) {
                    VoiceList(data = it, listState = voiceLazyPagingData?.listState)
                }
            }
            "quote" -> {
                SwipeRefreshLayout(data = quoteLazyPagingData?.items) {
                    QuoteList(data = it, listState = quoteLazyPagingData?.listState)
                }
            }
            "chatroom" -> {
                SwipeRefreshLayout(data = chatroomLazyPagingData?.items) {
                    ChatroomList(data = it, listState = chatroomLazyPagingData?.listState)
                }
            }
        }
    }
}