package com.stone.movieticket.view_holder

import android.content.res.ColorStateList
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.TimeslotVO
import com.stone.movieticket.delegate.TimeSlotDelegate
import kotlinx.android.synthetic.main.view_holder_movie_time_item.view.*

class MovieTimeItemViewHolder(itemView: View, timeSlotDelegate: TimeSlotDelegate) : RecyclerView.ViewHolder(itemView) {
    private lateinit var timeslotVO: TimeslotVO
    fun bindData(timeslotVO: TimeslotVO) {
        this.timeslotVO =timeslotVO
        itemView.tvMovieTime.text=timeslotVO.startTime
                if (timeslotVO.isSelected) {
                    itemView.tvMovieTime.apply {
                        setTextColor(resources.getColor(R.color.white))
                        backgroundTintList =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color.colorAccent
                                )
                            )
                    }
                }else{
                    itemView.tvMovieTime.apply {
                        setTextColor(resources.getColor(R.color.secondaryTextBlackColor))
                        backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(itemView.context,R.color.white))
                    }
                }
    }
    init {
        itemView.tvMovieTime.setOnClickListener {

            timeslotVO.cinemaDayTimeslotId?.let { it1 -> timeSlotDelegate.onTapTimeSlot(timeSlotId = it1)
                Log.i("Goooo", it1.toString())
            }
//            Log.i("Goooo", "click")
//            Toast.makeText(it.context,itemView.tvMovieTime.text.toString() , Toast.LENGTH_SHORT).show()
        }
    }
}