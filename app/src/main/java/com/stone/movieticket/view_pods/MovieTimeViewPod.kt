package com.stone.movieticket.view_pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.stone.movieticket.adapter.MovieTimeAdapter
import kotlinx.android.synthetic.main.activity_choose_movie_time.view.*

class MovieTimeViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var mMovieTimeAdapter: MovieTimeAdapter

    fun setUpMovieTime(size: Int,title:String,sampleText:String){
        //tvTitle.text = title
       // mMovieTimeAdapter = MovieTimeAdapter(size,sampleText)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
//        rvMovieTime.layoutManager=GridLayoutManager(context,3)
//        rvMovieTime.adapter = mMovieTimeAdapter

    }
}