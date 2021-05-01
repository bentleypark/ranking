package com.bentley.codingtest.data

import com.bentley.codingtest.data.model.RankingInfo
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://static.wippy.io/c/nrise_data/"
    }

    @GET("nrise_data.json")
    suspend fun fetchRankInfo(): RankingInfo
}