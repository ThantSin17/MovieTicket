package com.stone.movieticket.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.view_holder.MovieTimeItemViewHolder
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*
import kotlinx.android.synthetic.main.view_holder_movie_time_item.view.*

class MovieTimeItemAdapter(val dataList: List<Any>) :
    RecyclerView.Adapter<MovieTimeItemViewHolder>() {

    var selectedItem = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_time_item, parent, false)
        return MovieTimeItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieTimeItemViewHolder, position: Int) {
        holder.itemView.tvMovieTime.text = dataList[position].toString()


        holder.itemView.tvMovieTime.setOnClickListener {
            selectedItem = position
            notifyDataSetChanged()
        }

        //Toast.makeText(it.context,dataList[position].toString() , Toast.LENGTH_SHORT).show()
        if (selectedItem == position) {
            holder.itemView.tvMovieTime.apply {

                setTextColor(resources.getColor(R.color.white))
                backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context,R.color.colorAccent))
            }
        } else {
            holder.itemView.tvMovieTime.apply {
                text = dataList[position].toString()
                setTextColor(resources.getColor(R.color.secondaryTextBlackColor))
                backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context,R.color.white))
            }
        }

    }


    override fun getItemCount(): Int {
        return dataList.size
    }
}