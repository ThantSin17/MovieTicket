package com.stone.movieticket.data.vos


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.stone.movieticket.persistance.typeconverters.*
import com.thurainx.movieticketapp.data.vos.ProductionCompanyAndCountryVO
import com.thurainx.movieticketapp.data.vos.SpokenLanguageVO
import java.io.Serializable

@Entity(tableName = "movies")
@TypeConverters(
    GenreIdListTypeConverter::class,
    GenreListTypeConverter::class,
    ActorTypeConverter::class,
    ProductionCountryTypeConverter::class,
    SpokenLanguageTypeConverter::class
)
data class MovieVO(
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "genre_ids")
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String?,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "video")
    @SerializedName("video")
    val video: Boolean?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double?,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int?,

    @ColumnInfo(name = "budget")
    @SerializedName("budget")
    val budget: Int?,

    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres: List<String>?,

    @ColumnInfo(name = "casts")
    @SerializedName("casts")
    val casts: List<CastVO>?,

    @ColumnInfo(name = "production_companies")
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyAndCountryVO>?,

    @ColumnInfo(name = "production_countries")
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCompanyAndCountryVO>?,

    @ColumnInfo(name = "spoken_languages")
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageVO>?,

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    val rating: Double?,

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    val runtime: Int?,

    @ColumnInfo(name = "type")
    @SerializedName("type")
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