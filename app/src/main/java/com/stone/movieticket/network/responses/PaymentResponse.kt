package com.stone.movieticket.network.responses

import com.google.gson.annotations.SerializedName
import com.stone.movieticket.data.vos.PaymentVO
import com.stone.movieticket.data.vos.SnackVO

data class PaymentResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<PaymentVO>,

)
