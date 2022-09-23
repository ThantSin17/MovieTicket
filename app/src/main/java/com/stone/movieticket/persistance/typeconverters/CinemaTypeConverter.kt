package com.stone.movieticket.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieticket.data.vos.CinemaVO

class CinemaTypeConverter {
    @TypeConverter
    fun toString(genreIdList: List<CinemaVO>?): String {
        return Gson().toJson(genreIdList)
    }

    @TypeConverter
    fun toGenreIdList(jsonStr: String) : List<CinemaVO>?{
        val type = object : TypeToken<List<CinemaVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}