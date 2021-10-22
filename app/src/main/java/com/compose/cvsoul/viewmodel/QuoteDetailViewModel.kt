package com.compose.cvsoul.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.compose.cvsoul.repository.QuoteDisplayRepository
import com.compose.cvsoul.repository.model.QuoteAlbumPlaylistModel
import com.compose.cvsoul.util.toast

class QuoteDetailViewModel: ViewModel() {
    private val _data = MutableLiveData<QuoteAlbumPlaylistModel>(null)

    val data: LiveData<QuoteAlbumPlaylistModel> = _data

    suspend fun fetchQuotePlaylistDetail(_id: String) {
        rxLifeScope.launch({
            val resp = QuoteDisplayRepository.fetchQuoteAlbumDetail(_id)
            _data.value = resp
        }, {
            toast(it.message)
        })
    }
}