package com.stone.movieticket.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class MovieVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    val rating: String?,

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<String>?,

    @SerializedName("casts")
    @ColumnInfo(name = "casts")
    val casts: List<CastVO>?,

    @ColumnInfo(name = "type")
    var type: String?,


) :Serializable{
    fun getRatingBaseOnFiveStars():Float{
        return  rating?.toDouble()?.div(2)?.toFloat() ?: 0.0f
    }
    fun getGenresAsCommaSeparatedString():String{
        return genres?.map { it }?.joinToString( "," ) ?: ""
    }
    fun getDuration():String{
        val hour= runtime?.div(60)
        val minutes= runtime?.rem(60)
        return "$hour h $minutes m"
    }


}


const val NOW_PLAYING = "NOW_PLAYING"
const val COMING_SOON = "COMING_SOON"