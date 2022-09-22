package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.stone.movieticket.R
import com.stone.movieticket.adapter.CastAdapter
import com.stone.movieticket.adapter.MovieCategoryAdapter
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import com.stone.movieticket.data.vos.MovieVO
import com.stone.movieticket.utils.BASE_IMG_URL
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {
    lateinit var mMovieCategoryAdapter: MovieCategoryAdapter
    lateinit var mCastAdapter: CastAdapter
    private val mMovieTicketModel: MovieTicketModel = MovieTicketModelImpl
    private var movieId: Int? = 0
    private lateinit var movieVO: MovieVO

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun getInstance(context: Context, id: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, id)
            return intent
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
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true


        setUpRecyclerView()
        setUpListener()

        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
            requestData(it)
        }
    }

    private fun requestData(id: Int) {
        mMovieTicketModel.getMovieDetails(
            movieId = id.toString(),
            onSuccess = {
                movieVO = it
                bindData(movieVO)
            },
            onFailure = {
                Log.i("Goooe", it)
                showMessage(it)
            }
        )
    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$BASE_IMG_URL${movie.posterPath}")
            .into(ivMovie)

        tvMovieName.text = movie.originalTitle
        tvDuration.text = movie.getDuration()
        rbMovieRate.rating = movie.getRatingBaseOnFiveStars()
        tvImdb.text = "IMDB ${movie.rating}"
        mMovieCategoryAdapter.setNewData(movie.genres)
        tvPlotText.text = movie.overview
        mCastAdapter.setNewData(movie.casts)
    }

    private fun setUpListener() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
        btnGetTicket.setOnClickListener {
            startActivity(movieId?.let {
                ChooseMovieTimeActivity.getInstance(
                    this,
                    movieVO
                )
            })
        }
    }

    private fun setUpRecyclerView() {
        mMovieCategoryAdapter = MovieCategoryAdapter()
        rvMovieCategory.adapter = mMovieCategoryAdapter

        mCastAdapter = CastAdapter()
        rvCast.adapter = mCastAdapter

    }

    private fun showMessage(e: String) {
        Toast.makeText(applicationContext, e, Toast.LENGTH_SHORT).show()
    }
}