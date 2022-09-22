package com.stone.movieticket.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.adapter.MovieTimeItemAdapter
import com.stone.movieticket.data.vos.CinemaVO
import com.stone.movieticket.delegate.TimeSlotDelegate
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*

class MovieTimeViewHolder(itemView: View, timeSlotDelegate: TimeSlotDelegate) : RecyclerView.ViewHolder(itemView) {

    private lateinit var mMovieTimeItemAdapter:MovieTimeItemAdapter
    init {
         mMovieTimeItemAdapter = MovieTimeItemAdapter(timeSlotDelegate)
        itemView.rvMovieTime.adapter = mMovieTimeItemAdapter


    }
    fun bindData(cinema: CinemaVO){
        itemView.tvTitle.text=cinema.cinema
        mMovieTimeItemAdapter.setNewData(cinema)

    }
}