package com.stone.movieticket.data.vos

const val SEAT_TYPE_AVAILABLE = "available"
const val SEAT_TYPE_TOKEN = "taken"
const val SEAT_TYPE_TEXT = "text"
const val SEAT_TYPE_EMPTY = "space"

class MovieSeatVO(
    val title: String = "",
    private val type: String = ""
) {
    fun isMovieSeatAvailable(): Boolean? {

        return type === SEAT_TYPE_AVAILABLE
    }

    fun isMovieSeatTaken(): Boolean? {
        return type === SEAT_TYPE_TOKEN
    }

    fun isMovieSeatRowTitle(): Boolean? {
        return type === SEAT_TYPE_TEXT
    }

}