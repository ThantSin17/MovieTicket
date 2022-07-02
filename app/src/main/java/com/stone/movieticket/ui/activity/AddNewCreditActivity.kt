package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowInsetsControllerCompat
import com.stone.movieticket.R
import kotlinx.android.synthetic.main.activity_add_new_credit.*

class AddNewCreditActivity : AppCompatActivity() {
    companion object{
        fun getInstance(context: Context): Intent {
            return Intent(context,AddNewCreditActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_credit)

        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpListener()
    }

    private fun setUpListener() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
        btnAdd.setOnClickListener {
            onBackPressed()
        }
    }

}