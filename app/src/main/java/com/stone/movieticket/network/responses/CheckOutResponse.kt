package com.stone.movieticket.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieticket.data.vos.CardVO
import com.stone.movieticket.data.vos.CheckOutVO

data class CheckOutResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: CheckOutVO,
) {
}