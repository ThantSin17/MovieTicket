package com.stone.movieticket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.MovieVO
import com.stone.movieticket.delegate.MovieViewHolderDelegate
import com.stone.movieticket.view_holder.MovieViewHolder

class MovieAdapter(private val mMovieViewHolderDelegate: MovieViewHolderDelegate):RecyclerView.Adapter<MovieViewHolder>() {
    private var movieList:List<MovieVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_item,parent,false)
        return MovieViewHolder(view,mMovieViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (movieList.isNotEmpty()){
            holder.bindData(movieList[position])
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movies: List<MovieVO>) {
        movieList=movies;
        notifyDataSetChanged()
    }
}