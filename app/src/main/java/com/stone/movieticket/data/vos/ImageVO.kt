package com.stone.movieticket.data.vos

import com.google.gson.annotations.SerializedName

data class ImageVO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_image")
    val originalImage: String?,
    @SerializedName("w200_image")
    val w200Image: String?,
    @SerializedName("w300_image")
    val w300Image: String?,
    @SerializedName("w500_image")
    val w500Image: String
)