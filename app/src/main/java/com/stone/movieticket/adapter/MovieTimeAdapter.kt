package com.stone.movieticket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.view_holder.MovieTimeViewHolder
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*

class MovieTimeAdapter():RecyclerView.Adapter<MovieTimeViewHolder>() {
     val dataList = listOf(
         listOf("Available In", listOf("2D","3D","HMC")),
        listOf("GC : Golden City", listOf("9:30 AM","10:10AM","3:30","9:30 AM","10:10AM","3:30")),
        listOf("GC : West Point", listOf("3:30","9:30 AM","9:30 AM","10:10AM","10:10AM","3:30")),
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_time,parent,false)
        return MovieTimeViewHolder(view,dataList)
    }

    override fun onBindViewHolder(holder: MovieTimeViewHolder, position: Int) {
        holder.itemView.tvTitle.text = dataList[position][0].toString()
        holder.bindData(dataList[position][1] as List<Any>)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}