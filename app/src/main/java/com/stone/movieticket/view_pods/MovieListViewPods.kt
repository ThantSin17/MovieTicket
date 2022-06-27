package com.stone.movieticket.view_pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.movieticket.adapter.MovieAdapter
import com.stone.movieticket.delegate.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPods @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var movieAdapter: MovieAdapter
    lateinit var mDelegate: MovieViewHolderDelegate

    override fun onFinishInflate() {
//        setUpRecyclerView()
        super.onFinishInflate()
    }

    private fun setUpRecyclerView() {
        movieAdapter = MovieAdapter(mDelegate)
        rvMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvMovieList.adapter = movieAdapter
    }

    fun setUpMovieListViewPod(titleText: String,delegate: MovieViewHolderDelegate) {
        tvMovieCategory.text = titleText
        mDelegate = delegate
        setUpRecyclerView()
    }
}