package com.stone.movieticket.persistance.typeconverters


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.movieticket.data.vos.CastVO

class ActorTypeConverter {
    @TypeConverter
    fun toString(actorList: List<CastVO>?): String {
        return Gson().toJson(actorList)
    }

    @TypeConverter
    fun toActorList(jsonStr: String) : List<CastVO>?{
        val type = object : TypeToken<List<CastVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}