package com.stone.movieticket.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CinemaVO(
    @SerializedName("cinema")
    var cinema: String?,
    @SerializedName("cinema_id")
    var cinemaId: Int,
    @SerializedName("timeslots")
    var timeslots: List<TimeslotVO>
):Serializable