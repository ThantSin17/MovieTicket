package com.stone.movieticket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.CinemaVO
import com.stone.movieticket.data.vos.TimeslotVO
import com.stone.movieticket.delegate.TimeSlotDelegate
import com.stone.movieticket.view_holder.MovieTimeItemViewHolder

class MovieTimeItemAdapter(private val timeSlotDelegate: TimeSlotDelegate) :
    RecyclerView.Adapter<MovieTimeItemViewHolder>() {

    private var timeSlotList:List<TimeslotVO> = arrayListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_time_item, parent, false)
        return MovieTimeItemViewHolder(view,timeSlotDelegate)
    }

    override fun onBindViewHolder(holder: MovieTimeItemViewHolder, position: Int) {
        if (timeSlotList.isNotEmpty()){
            holder.bindData(timeSlotList[position])
        }
//        holder.itemView.tvMovieTime.text = dataList[position].toString()
//
//
//        holder.itemView.tvMovieTime.setOnClickListener {
//            selectedItem = position
//            notifyDataSetChanged()
//        }

        //Toast.makeText(it.context,dataList[position].toString() , Toast.LENGTH_SHORT).show()
//        if (selectedItem == position) {
//            holder.itemView.tvMovieTime.apply {
//
//                setTextColor(resources.getColor(R.color.white))
//                backgroundTintList =
//                    ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context,R.color.colorAccent))
//            }
//        } else {
//            holder.itemView.tvMovieTime.apply {
//                text = dataList[position].toString()
//                setTextColor(resources.getColor(R.color.secondaryTextBlackColor))
//                backgroundTintList =
//                    ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context,R.color.white))
//            }
//        }

    }


    override fun getItemCount(): Int {
        return timeSlotList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cinema: CinemaVO) {
        timeSlotList=cinema.timeslots
        notifyDataSetChanged()
    }
}