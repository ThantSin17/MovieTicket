package com.stone.movieticket.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieticket.data.vos.MovieVO

data class MovieDetailResponse (
    @SerializedName("code")
    val code:String?,
    @SerializedName("message")
    val message:String?,
    @SerializedName("data")
    val data:MovieVO
    )