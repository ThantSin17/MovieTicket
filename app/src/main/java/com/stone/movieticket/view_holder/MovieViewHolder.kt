package com.stone.movieticket.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stone.movieticket.data.vos.MovieVO
import com.stone.movieticket.delegate.MovieViewHolderDelegate
import com.stone.movieticket.utils.BASE_IMG_URL
import kotlinx.android.synthetic.main.view_holder_movie_item.view.*

class MovieViewHolder(itemView: View,mMovieViewHolderDelegate: MovieViewHolderDelegate) :RecyclerView.ViewHolder(itemView) {

    private var movie:MovieVO? = null
    fun bindData(movieVO: MovieVO) {
        movie=movieVO
        Glide.with(itemView.context)
            .load("$BASE_IMG_URL${movieVO.posterPath}")
            .into(itemView.ivImg)


        itemView.tvMovieName.text=movieVO.originalTitle
        itemView.tvMovieType.text= movieVO.getGenresAsCommaSeparatedString() ?: ""
//        itemView.tvMovieDuration.text=movieVO.releaseDate
    }

    init {
        itemView.setOnClickListener {

            movie?.let { movie -> mMovieViewHolderDelegate.onTapMovie(movie.id) }

        }
    }
}