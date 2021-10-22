package com.compose.cvsoul.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.compose.cvsoul.repository.QuoteDisplayRepository
import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.util.toast
import kotlinx.coroutines.flow.Flow

class QuoteListByTagViewModel(val listState: LazyListState): ViewModel() {
    private val _list = MutableLiveData<Flow<PagingData<QuoteAlbumDisplayModel>>>(null)

    val list: LiveData<Flow<PagingData<QuoteAlbumDisplayModel>>> = _list

    fun fetchQuoteAlbumPaginationList(cateId: String) {
        rxLifeScope.launch({
            val data = QuoteDisplayRepository.fetchQuoteAlbumPaginationList(cateId).cachedIn(viewModelScope)
            _list.value = data
        }, {
            toast(it.message)
        })
    }
}