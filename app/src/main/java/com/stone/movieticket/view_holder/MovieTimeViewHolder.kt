package com.stone.movieticket.view_holder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.adapter.DayListAdapter
import com.stone.movieticket.adapter.MovieTimeItemAdapter
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieTimeViewHolder(itemView: View, dataList: List<List<Any>>) : RecyclerView.ViewHolder(itemView) {
    lateinit var mMovieTimeItemAdapter: MovieTimeItemAdapter

    fun bindData(list:List<Any>){
        mMovieTimeItemAdapter = MovieTimeItemAdapter(list)
        itemView.rvMovieTime.adapter = mMovieTimeItemAdapter
    }
}