package com.bentley.codingtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bentley.codingtest.data.DataState
import com.bentley.codingtest.data.RankRepository
import com.bentley.codingtest.data.model.RankingInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject
constructor(
    private val rankRepository: RankRepository
) : ViewModel() {

    private val _rankInfo = MutableLiveData<DataState<RankingInfo>>()
    val rankInfo: LiveData<DataState<RankingInfo>> = _rankInfo

    fun fetchInitialData() {
        viewModelScope.launch {
            rankRepository.fetchRankInfo()
                .onEach { dataState ->
                    Timber.d(dataState.toString())
                    _rankInfo.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }
}