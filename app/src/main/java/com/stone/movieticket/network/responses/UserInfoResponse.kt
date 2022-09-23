package com.stone.movieticket.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieticket.data.vos.UserVO

data class UserInfoResponse(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: UserVO?,

    @SerializedName("token")
    val token: String?,
)