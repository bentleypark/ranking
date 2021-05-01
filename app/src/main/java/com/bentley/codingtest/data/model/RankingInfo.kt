package com.bentley.codingtest.data.model

import com.google.gson.annotations.SerializedName

data class RankingInfo(
    @SerializedName("location")
    val location: String,
    @SerializedName("my_profile")
    val profileImg: String,
    @SerializedName("my_rank")
    val myRank: Int,
    @SerializedName("segment")
    val segment: String,
    @SerializedName("rank_list")
    val totalRank: List<Rank>,
)

data class Rank(
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("profile_image")
    val profileImg: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("is_me")
    val isMe: Boolean,
    @SerializedName("diff_rank")
    val diffRank: Int,
)
