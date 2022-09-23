package com.stone.movieticket.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thurainx.movieticketapp.data.vos.ProductionCompanyAndCountryVO
import com.thurainx.movieticketapp.data.vos.SpokenLanguageVO

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguageList: List<SpokenLanguageVO>?): String {
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokenLanguageList(jsonStr: String) : List<SpokenLanguageVO>?{
        val type = object : TypeToken<List<SpokenLanguageVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}