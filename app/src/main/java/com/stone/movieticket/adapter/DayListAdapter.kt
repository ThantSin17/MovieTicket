package com.stone.movieticket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.view_holder.DayListViewHolder
import com.stone.movieticket.view_holder.MovieViewHolder
import kotlinx.android.synthetic.main.view_holder_day.view.*

class DayListAdapter : RecyclerView.Adapter<DayListViewHolder>() {
    var selectedPosition: Int = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_day, parent, false)
        return DayListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayListViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
        if (selectedPosition==position){
            holder.itemView.tvDayText.apply {
                this.setTextColor(ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white)
                )
            }
            holder.itemView.tvDateText.apply {
                this.setTextColor(ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white)
                )
                this.textSize=24f
            }
        }else{
            holder.itemView.tvDayText.apply {
                this.setTextColor(ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.secondaryTextBlackColor)
                )
                this.textSize=20f
            }
            holder.itemView.tvDateText.apply {
                this.setTextColor(ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.secondaryTextBlackColor)
                )
                this.textSize=20f
            }
        }
    }

    override fun getItemCount(): Int {
        return 18
    }
}