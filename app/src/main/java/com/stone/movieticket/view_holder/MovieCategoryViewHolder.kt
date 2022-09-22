package com.stone.movieticket.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_movie_category.view.*

class MovieCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(genre: String) {
        itemView.tvMovieCategory.text=genre
    }
}