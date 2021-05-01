package com.bentley.codingtest.data

import com.bentley.codingtest.data.model.Rank
import com.bentley.codingtest.data.model.RankingInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RankRepository constructor(private val apiService: ApiService) {

    suspend fun fetchRankInfo(): Flow<DataState<RankingInfo>> =
        flow {

            emit(DataState.Loading)
            delay(1000)
            try {
                val resultList = apiService.fetchRankInfo()
                emit(DataState.Success(resultList))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}