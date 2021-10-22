package com.compose.cvsoul.repository.service

import com.compose.cvsoul.repository.model.ChatroomDisplayModel
import com.compose.cvsoul.repository.model.PaginationList
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

object ChatroomService {
    /**
     * 获取聊天室分页数据
     * @param page 页码
     * @param pageSize 分页大小
     * @param title 标题
     */
    suspend fun fetchChatroomPaginationList(page: Int, pageSize: Int, title: String? = null): PaginationList<ChatroomDisplayModel> {
        return RxHttp
            .getEncrypt("/chatroom/playlist/display")
            .addQuery("page", page)
            .addQuery("pageSize", pageSize)
            .addQuery("title", title)
            .toResponse<PaginationList<ChatroomDisplayModel>>()
            .await()
    }
}