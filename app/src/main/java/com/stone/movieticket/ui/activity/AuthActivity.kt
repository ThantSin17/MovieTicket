package com.stone.movieticket.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stone.movieticket.data.model.MovieTicketModelImpl

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mMovieTicketModel = MovieTicketModelImpl

        if(mMovieTicketModel.token.isNullOrEmpty()){
            val intent = WelcomeActivity.getIntent(this)
            startActivity(intent)
        }else{
            val intent = MainActivity.getIntent(this)
            startActivity(intent)
        }
        finish()
    }
}