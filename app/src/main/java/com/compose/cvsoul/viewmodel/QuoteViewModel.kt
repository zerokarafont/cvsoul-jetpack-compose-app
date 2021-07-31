package com.compose.cvsoul.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.compose.cvsoul.repository.model.CateModel
import com.compose.cvsoul.repository.service.CateService
import com.compose.cvsoul.util.toast

class QuoteViewModel: ViewModel() {
    private val _isLoading = MutableLiveData(false)
    private val _cates     = MutableLiveData<List<CateModel>?>(null)

    val cates:     LiveData<List<CateModel>?> = _cates
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun fetchAllCates() {
        rxLifeScope.launch({
            val data = CateService.fetchAllCates()
            _cates.value = data
        }, {
           toast(it.message)
        }, {
            _isLoading.value = true
        }, {
            _isLoading.value = false
        })
    }
}