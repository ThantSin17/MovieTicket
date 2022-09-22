package com.stone.movieticket.view_holder

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.MovieSeatVO
import com.stone.movieticket.delegate.MovieSeatsDelegate
import kotlinx.android.synthetic.main.view_holder_movie_seat.view.*

class MovieSeatsViewHolder(itemView: View, movieSeatsDelegate: MovieSeatsDelegate) : RecyclerView.ViewHolder(itemView) {

    private var movieSeatVO:MovieSeatVO?=null
    fun bindData(data :MovieSeatVO){
        movieSeatVO=data
        when{
            data.isMovieSeatAvailable()==true ->{

                itemView.tvMovieSeat.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_avilable
                )
            }
            data.isMovieSeatTaken() ==true ->{
                itemView.tvMovieSeat.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_taken
                )
            }
            data.isMovieSeatRowTitle() ==true ->{
                itemView.tvMovieSeat.visibility = View.VISIBLE
                itemView.tvMovieSeat.text = data.title
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }
            data.isMovieSeatSelected() ->{
                itemView.tvMovieSeat.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_selected
                )
            }
            else->{
                itemView.tvMovieSeat.visibility = View.GONE
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.transparent
                    )
                )
            }
        }

    }
    init {
        itemView.setOnClickListener {
            movieSeatVO?.let { movieSeat ->
                movieSeatsDelegate.onTapMovieSeats(movieSeat.id,movieSeat.title)
            }
        }
    }
}