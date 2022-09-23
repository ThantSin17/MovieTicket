package com.stone.movieticket.data.vos

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.stone.movieticket.persistance.typeconverters.TimeSlotTypeConverter
import java.io.Serializable

@TypeConverters(
    TimeSlotTypeConverter::class
)
data class CinemaVO(
    @SerializedName("cinema")
    var cinema: String?,
    @SerializedName("cinema_id")
    var cinemaId: Int,
    @SerializedName("timeslots")
    var timeslots: List<TimeslotVO>
):Serializable