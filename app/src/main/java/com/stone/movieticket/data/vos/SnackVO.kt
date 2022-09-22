package com.stone.movieticket.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SnackVO(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("price")
    val price:Int,
    @SerializedName("image")
    val image:String?,
    @SerializedName("quantity")
    var quantity:Int=0,
    var currentValue:Int=0

) :Serializable{
}