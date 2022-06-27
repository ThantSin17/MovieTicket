package com.stone.movieticket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.delegate.MovieViewHolderDelegate
import com.stone.movieticket.view_holder.MovieViewHolder

class MovieAdapter(private val mMovieViewHolderDelegate: MovieViewHolderDelegate):RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_item,parent,false)
        return MovieViewHolder(view,mMovieViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}