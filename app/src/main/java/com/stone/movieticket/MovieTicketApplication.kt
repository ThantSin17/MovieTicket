package com.stone.movieticket

import android.app.Application
import com.stone.movieticket.data.model.MovieTicketModelImpl

class MovieTicketApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        MovieTicketModelImpl.initDatabase(applicationContext)
    }
}