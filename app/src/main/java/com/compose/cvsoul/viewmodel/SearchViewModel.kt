package com.compose.cvsoul.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.compose.cvsoul.repository.ChatroomRepository
import com.compose.cvsoul.repository.QuoteDisplayRepository
import com.compose.cvsoul.repository.VoiceRepository
import com.compose.cvsoul.repository.model.FlowPagingData
import com.compose.cvsoul.repository.model.TagModel
import com.compose.cvsoul.repository.service.TagService
import com.compose.cvsoul.util.toast

class SearchViewModel(private val listStateGroup: List<LazyListState>): ViewModel() {
    private val _tags           = MutableLiveData<List<TagModel>?>(null)
    private val _searchResponse = MutableLiveData<List<FlowPagingData<*>>?>(null)

    val tags:           LiveData<List<TagModel>?> = _tags
    val searchResponse: LiveData<List<FlowPagingData<*>>?> = _searchResponse

    suspend fun fetchAllTags() {
        rxLifeScope.launch({
            val data = TagService.fetchAllTags()
            _tags.value = data
        }, {
            toast(it.message)
        })
    }

    fun fetchSearchResultPaginationList(searchInput: String) {
        rxLifeScope.launch({
            val voicePagingData = VoiceRepository.fetchVoicePaginationList(searchInput).cachedIn(viewModelScope)
            val quotePagingData = QuoteDisplayRepository.fetchQuoteAlbumPaginationList(title = searchInput).cachedIn(viewModelScope)
            val chatroomPagingData = ChatroomRepository.fetchChatroomPaginationList(searchInput).cachedIn(viewModelScope)
            _searchResponse.value = listOf(
                FlowPagingData(flow = voicePagingData, listState = listStateGroup[0]),
                FlowPagingData(flow = quotePagingData, listState = listStateGroup[1]),
                FlowPagingData(flow = chatroomPagingData, listState = listStateGroup[2])
            )
        }, {
            toast(it.message)
        })
    }

    fun clearSearchResponse() {
        _searchResponse.value = null
    }
}