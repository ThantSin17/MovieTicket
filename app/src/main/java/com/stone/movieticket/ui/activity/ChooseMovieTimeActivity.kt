package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.stone.movieticket.R
import com.stone.movieticket.adapter.DayListAdapter
import com.stone.movieticket.adapter.MovieTimeAdapter
import kotlinx.android.synthetic.main.activity_choose_movie_time.*

class ChooseMovieTimeActivity : AppCompatActivity() {
    lateinit var mDayListAdapter: DayListAdapter
    lateinit var mMovieTimeAdapter: MovieTimeAdapter
    companion object{
        fun getInstance(context: Context):Intent{
            return Intent(context,ChooseMovieTimeActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_movie_time)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }


        //status bar icon color
//        val docker = window.decorView
//        val wic = WindowInsetsControllerCompat(window,docker)
//        wic.isAppearanceLightStatusBars = true

        setUpRecyclerView()
        setUpViewPod()
        setUpListener()
    }

    private fun setUpListener() {
        btnNext.setOnClickListener {
            startActivity(ComboSetActivity.getInstance(this))
        }
    }

    private fun setUpViewPod() {
    }

    private fun setUpRecyclerView() {
        mDayListAdapter = DayListAdapter()
        rvDay.adapter = mDayListAdapter
        mMovieTimeAdapter = MovieTimeAdapter()
        rvMovieTime.adapter = mMovieTimeAdapter
    }
}