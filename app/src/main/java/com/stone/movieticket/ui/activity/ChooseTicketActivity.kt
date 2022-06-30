package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.google.android.flexbox.*
import com.stone.movieticket.R
import com.stone.movieticket.adapter.MovieTicketAdapter
import kotlinx.android.synthetic.main.activity_choose_ticket_activity.*

class ChooseTicketActivity : AppCompatActivity() {
    lateinit var mMovieTicketAdapter: MovieTicketAdapter
    companion object{
        fun getInstance(context: Context):Intent{
            return Intent(context,ChooseTicketActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_ticket_activity)

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

    }

    private fun setUpRecyclerView() {
        mMovieTicketAdapter = MovieTicketAdapter()
        val size = mMovieTicketAdapter.itemCount
//        val layoutManager = FlexboxLayoutManager(this,FlexDirection.COLUMN).apply {
//            justifyContent = JustifyContent.CENTER
//            alignItems = AlignItems.CENTER
//            flexDirection = FlexDirection.ROW
//            flexWrap = FlexWrap.WRAP
//        }
        val layoutManager =GridLayoutManager(this,8,GridLayoutManager.VERTICAL,true)

//        layoutManager.spanSizeLookup = object :SpanSizeLookup(){
//            override fun getSpanSize(position: Int): Int {
//                if (position >50 ){
//                    return 6
//                }
//                return 1
//            }
//        }
        rvTicket.layoutManager= layoutManager
        rvTicket.adapter = mMovieTicketAdapter
        rvTicket.setHasFixedSize(false)

    }
}