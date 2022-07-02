package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import com.stone.movieticket.R
import kotlinx.android.synthetic.main.activity_awesome.*

class AwesomeActivity : AppCompatActivity() {
    companion object{
        fun getInstance(context: Context):Intent{
            return Intent(context,AwesomeActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_awesome)


        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpListener()
    }

    private fun setUpListener() {
        ivBack.setOnClickListener {
            startActivity(MainActivity.getInstance(this))
            finish()
        }
    }

}