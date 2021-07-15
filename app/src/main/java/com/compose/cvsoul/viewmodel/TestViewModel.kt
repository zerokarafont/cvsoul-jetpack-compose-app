package com.compose.cvsoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.rxlife.coroutine.RxLifeScope
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

class TestViewModel : ViewModel() {
    suspend fun test() {
        rxLifeScope.launch {
            RxHttp
                .getEncrypt("/rest/one")
                .addQuery("age", 2)
                .toResponse<String>()
                .await()
        }
    }
}