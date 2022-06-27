package com.stone.movieticket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.view_holder.MovieCategoryViewHolder

class MovieCategoryAdapter:RecyclerView.Adapter<MovieCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_category,parent,false)
        return  MovieCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCategoryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}