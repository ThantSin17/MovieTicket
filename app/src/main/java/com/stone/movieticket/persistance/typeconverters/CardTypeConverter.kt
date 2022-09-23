package com.stone.movieticket.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieticket.data.vos.CardVO

class CardTypeConverter {
    @TypeConverter
    fun toString(cardList: List<CardVO>?): String {
        return Gson().toJson(cardList)
    }

    @TypeConverter
    fun toCardList(jsonStr: String) : List<CardVO>?{
        val type = object : TypeToken<List<CardVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}