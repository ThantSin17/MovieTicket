package com.stone.movieticket.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieticket.data.vos.TimeslotVO

class TimeSlotTypeConverter {
    @TypeConverter
    fun toString(timeslotList: List<TimeslotVO>?): String {
        return Gson().toJson(timeslotList)
    }

    @TypeConverter
    fun toTimeslotList(jsonStr: String) : List<TimeslotVO>?{
        val type = object : TypeToken<List<TimeslotVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}