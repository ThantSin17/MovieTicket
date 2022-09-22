package com.stone.movieticket.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieticket.data.vos.MovieSeatVO
import com.stone.movieticket.data.vos.ProfileVO

data class MovieSeatResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<List<MovieSeatVO>>,
) {
}