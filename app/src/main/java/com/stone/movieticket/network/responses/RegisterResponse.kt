package com.stone.movieticket.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieticket.data.vos.MovieVO
import com.stone.movieticket.data.vos.ProfileVO

data class RegisterResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: ProfileVO,
    @SerializedName("token")
    val token: String?,
)