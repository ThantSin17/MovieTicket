package com.stone.movieticket.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.delegate.MovieViewHolderDelegate

class MovieViewHolder(itemView: View,mMovieViewHolderDelegate: MovieViewHolderDelegate) :RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mMovieViewHolderDelegate.onTapMovie()
        }
    }
}