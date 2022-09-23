package com.stone.movieticket.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stone.movieticket.data.vos.DateAndCinemaVO
import com.stone.movieticket.data.vos.MovieVO

@Dao
interface CinemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDateAndCinema(dateAndCinemaVO: DateAndCinemaVO)

    @Query("SELECT * FROM DateAndCinema where date=:date")
    fun getCinemaByDate(date: String): DateAndCinemaVO
}