package com.compose.cvsoul.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.compose.cvsoul.repository.QuoteDisplayRepository
import com.compose.cvsoul.repository.model.CateModel
import com.compose.cvsoul.repository.model.QuoteAlbumDisplayModel
import com.compose.cvsoul.util.toast
import kotlinx.coroutines.flow.Flow

class QuoteViewModel(val listState: LazyListState): ViewModel() {
    private val _cates            = MutableLiveData<List<CateModel>?>(null)
    private val _list             = MutableLiveData<Flow<PagingData<QuoteAlbumDisplayModel>>>(null)
    private val _currentViewPager = MutableLiveData(0)
    private val _currentCateId           = MutableLiveData("")

    val cates:            LiveData<List<CateModel>?> = _cates
    val list:             LiveData<Flow<PagingData<QuoteAlbumDisplayModel>>> = _list
    val currentCateId:    LiveData<String> = _currentCateId
    val currentViewPager: LiveData<Int> = _currentViewPager

    suspend fun fetchAllCates() {
        rxLifeScope.launch({
            val data = QuoteDisplayRepository.fetchAllCates()
            _cates.value = data
            _currentCateId.value = data[0]._id
        }, {
           toast(it.message)
        })
    }

    fun fetchQuoteAlbumPaginationList(cateId: String, title: String? = null) {
        val data = QuoteDisplayRepository.fetchQuoteAlbumPaginationList(cateId, title).cachedIn(viewModelScope)
        _list.value = data
    }

    fun changeCurrentCateId(cateId: String) {
        _currentCateId.value = cateId
    }

    fun changeCurrentViewPager(page: Int) {
        _currentViewPager.value = page
    }
}