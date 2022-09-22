package com.stone.movieticket.data.vos

import com.google.gson.annotations.SerializedName

const val SEAT_TYPE_AVAILABLE = "available"
const val SEAT_TYPE_TOKEN = "taken"
const val SEAT_TYPE_TEXT = "text"
const val SEAT_TYPE_EMPTY = "space"
const val SEAT_TYPE_SELECTED = "selected"

class MovieSeatVO(
    @SerializedName("seat_name")
    val seatName: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("symbol")
    val title: String = "",
    @SerializedName("price")
    val price: Int,
    @SerializedName("id")
    val id: Int,
    var isAlreadySelected:Boolean=false
) {
    fun isMovieSeatAvailable(): Boolean? {

        return type == SEAT_TYPE_AVAILABLE
    }
    fun isMovieSeatSelected():Boolean{
        return  return type == SEAT_TYPE_SELECTED
    }

    fun isMovieSeatTaken(): Boolean? {
        return type == SEAT_TYPE_TOKEN
    }

    fun isMovieSeatRowTitle(): Boolean? {
        return type == SEAT_TYPE_TEXT
    }
    fun isSelected(id:Int,title: String):Boolean{
        return id==this.id && title==this.title
    }

}