package com.stone.movieticket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.MovieSeatVO
import com.stone.movieticket.view_holder.MovieSeatsViewHolder

class MovieSeatAdapter(private var mMovieSeatList :List<MovieSeatVO> = listOf()): RecyclerView.Adapter<MovieSeatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie_seat, parent, false)
            return MovieSeatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieSeatsViewHolder, position: Int) {
        if (mMovieSeatList.isNotEmpty()){
            holder.bindData(mMovieSeatList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieSeatList.count()
       // return seatList.size
    }

    fun setNewData(movieSeat: List<MovieSeatVO>){
        this.mMovieSeatList = movieSeat
        notifyDataSetChanged()
    }


}