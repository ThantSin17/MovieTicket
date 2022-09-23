package com.stone.movieticket.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stone.movieticket.data.vos.DateAndCinemaVO
import com.stone.movieticket.data.vos.MovieVO
import com.stone.movieticket.data.vos.UserVO
import com.stone.movieticket.persistance.daos.CinemaDao
import com.stone.movieticket.persistance.daos.MovieDao
import com.stone.movieticket.persistance.daos.UserInfoDao

@Database(entities = [DateAndCinemaVO::class,MovieVO::class,UserVO::class], version = 2, exportSchema = false)
abstract class MovieTicketDatabase : RoomDatabase() {
    companion object{
        const val DB_NAME = "THE_MOVIE_TICKET_DB"
        var instant : MovieTicketDatabase? = null

        fun getInstant(context: Context): MovieTicketDatabase?{
            when(instant){
                null -> {
                    instant = Room.databaseBuilder(context, MovieTicketDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instant
        }

    }
    abstract fun userDao(): UserInfoDao
    abstract fun movieDao() : MovieDao
    abstract fun dateAndCinemaDao() : CinemaDao

}