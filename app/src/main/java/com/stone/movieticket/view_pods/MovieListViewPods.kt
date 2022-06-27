package com.stone.movieticket.view_pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.movieticket.adapter.MovieAdapter
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPods @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var movieAdapter: MovieAdapter

    override fun onFinishInflate() {
        setUpRecyclerView()
        super.onFinishInflate()
    }

    private fun setUpRecyclerView() {
        movieAdapter = MovieAdapter()
        rvMovieList.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rvMovieList.adapter = movieAdapter
    }

    fun setUpMovieListViewPod(titleText:String){
        tvMovieCategory.text=titleText
        setUpRecyclerView()
    }
}