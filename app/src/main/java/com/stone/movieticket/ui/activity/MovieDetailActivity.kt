package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import com.stone.movieticket.R
import com.stone.movieticket.adapter.CastAdapter
import com.stone.movieticket.adapter.MovieCategoryAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_pod_movie_list.*

class MovieDetailActivity : AppCompatActivity() {
    lateinit var mMovieCategoryAdapter: MovieCategoryAdapter
    lateinit var mCastAdapter: CastAdapter
    companion object{
        fun getInstance(context: Context):Intent{
            return Intent(context,MovieDetailActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

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
        ivBack.setOnClickListener {
            onBackPressed()
        }
        btnGetTicket.setOnClickListener {
            startActivity(ChooseMovieTimeActivity.getInstance(this))
        }
    }

    private fun setUpRecyclerView() {
        mMovieCategoryAdapter = MovieCategoryAdapter()
        rvMovieCategory.adapter = mMovieCategoryAdapter

        mCastAdapter = CastAdapter()
        rvCast.adapter = mCastAdapter

    }
}