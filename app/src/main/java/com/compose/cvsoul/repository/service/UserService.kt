package com.compose.cvsoul.repository.service

import com.compose.cvsoul.repository.model.ProfileModel
import rxhttp.wrapper.cahce.CacheMode
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

object UserService {
    /**
     * 获取个人中心用户资料
     * 头像 用户名
     */
    suspend fun fetchProfile(): ProfileModel {
        return RxHttp
            .getEncrypt("/user/profile")
            .setCacheMode(CacheMode.ONLY_NETWORK)
            .setDecoderEnabled(false)
            .toResponse<ProfileModel>()
            .await()
    }
}