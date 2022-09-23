package com.stone.movieticket.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.stone.movieticket.persistance.typeconverters.CinemaTypeConverter

@Entity(tableName = "DateAndCinema")
@TypeConverters(
    CinemaTypeConverter::class
)
data class DateAndCinemaVO(
    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "cinema")
    var cinema : List<CinemaVO>? =null
)