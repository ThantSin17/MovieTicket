package com.stone.movieticket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.CinemaVO
import com.stone.movieticket.delegate.TimeSlotDelegate
import com.stone.movieticket.view_holder.MovieTimeViewHolder

class MovieTimeAdapter(private val timeSlotDelegate: TimeSlotDelegate):RecyclerView.Adapter<MovieTimeViewHolder>() {
     val dataList = listOf(
         listOf("Available In", listOf("2D","3D","HMC")),
        listOf("GC : Golden City", listOf("9:30 AM","10:10AM","3:30","9:30 AM","10:10AM","3:30")),
        listOf("GC : West Point", listOf("3:30","9:30 AM","9:30 AM","10:10AM","10:10AM","3:30")),
    )
    private var cinemaList:List<CinemaVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_time,parent,false)
        return MovieTimeViewHolder(view,timeSlotDelegate)
    }

    override fun onBindViewHolder(holder: MovieTimeViewHolder, position: Int) {
//        holder.itemView.tvTitle.text = dataList[position][0].toString()
//        holder.bindData(dataList[position][1] as List<Any>)
        if (cinemaList.isNotEmpty()){
            holder.bindData(cinemaList[position])
        }
    }

    override fun getItemCount(): Int {
        return cinemaList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cinemas: List<CinemaVO>) {
        this.cinemaList=cinemas
        notifyDataSetChanged()
    }
}