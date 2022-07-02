package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.stone.movieticket.R
import com.stone.movieticket.adapter.MovieSeatAdapter
import com.stone.movieticket.utils.DUMMY_SEAT_LIST
import kotlinx.android.synthetic.main.activity_choose_movie_seats_activity.*

class ChooseMovieSeatActivity : AppCompatActivity() {
    lateinit var mMovieTicketAdapter: MovieSeatAdapter
    companion object{
        fun getInstance(context: Context):Intent{
            return Intent(context,ChooseMovieSeatActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_movie_seats_activity)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window,docker)
        wic.isAppearanceLightStatusBars = true

        setUpRecyclerView()
        setUpListener()
    }

    private fun setUpListener() {
        ivBack.setOnClickListener { onBackPressed() }
        btnBuyTicket.setOnClickListener {
            startActivity(ComboSetActivity.getInstance(this))
        }
    }

    private fun setUpRecyclerView() {
        mMovieTicketAdapter = MovieSeatAdapter()
        val layoutManager =GridLayoutManager(this,10)
        rvTicket.layoutManager= layoutManager
        rvTicket.adapter = mMovieTicketAdapter
        mMovieTicketAdapter.setNewData(DUMMY_SEAT_LIST)

    }
}