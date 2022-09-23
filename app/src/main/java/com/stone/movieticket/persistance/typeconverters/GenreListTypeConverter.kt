package com.stone.movieticket.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListTypeConverter {
    @TypeConverter
    fun toString(genreList: List<String>?): String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(jsonStr: String) : List<String>?{
        val type = object : TypeToken<List<String>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}