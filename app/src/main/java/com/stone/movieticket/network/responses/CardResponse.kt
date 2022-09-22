package com.stone.movieticket.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieticket.data.vos.CardVO
import com.stone.movieticket.data.vos.ProfileVO

data class CardResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<CardVO>,
)