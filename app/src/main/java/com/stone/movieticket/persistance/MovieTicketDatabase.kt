package com.stone.movieticket.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stone.movieticket.data.vos.UserVO
import com.stone.movieticket.persistance.daos.UserInfoDao

@Database(entities = [UserVO::class], version = 1, exportSchema = false)
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

}