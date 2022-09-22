package com.stone.movieticket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.DateVO
import com.stone.movieticket.delegate.ChooseDateDelegate
import com.stone.movieticket.view_holder.DayListViewHolder
import kotlinx.android.synthetic.main.view_holder_day.view.*

class DayListAdapter(private val chooseDateDelegate: ChooseDateDelegate) : RecyclerView.Adapter<DayListViewHolder>() {
    private var dayList:List<DateVO> = arrayListOf()
    var selectedPosition: Int = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_day, parent, false)
        return DayListViewHolder(view,chooseDateDelegate)
    }

    override fun onBindViewHolder(holder: DayListViewHolder, position: Int) {

        if (dayList.isNotEmpty()){
            holder.bindData(dayList[position])
        }
//        holder.itemView.setOnClickListener {
//            selectedPosition = position
//            notifyDataSetChanged()
//        }
//        if (selectedPosition==position){
//            holder.itemView.tvDayText.apply {
//                this.setTextColor(ContextCompat.getColor(
//                    holder.itemView.context,
//                    R.color.white)
//                )
//                textSize=20f
//            }
//            holder.itemView.tvDateText.apply {
//                this.setTextColor(ContextCompat.getColor(
//                    holder.itemView.context,
//                    R.color.white)
//                )
//                this.textSize=24f
//            }
//        }else{
//            holder.itemView.tvDayText.apply {
//                this.setTextColor(ContextCompat.getColor(
//                    holder.itemView.context,
//                    R.color.secondaryTextWhite30Color)
//                )
//                this.textSize=18f
//            }
//            holder.itemView.tvDateText.apply {
//                this.setTextColor(ContextCompat.getColor(
//                    holder.itemView.context,
//                    R.color.secondaryTextWhite30Color)
//                )
//                this.textSize=18f
//            }
//        }
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(list: MutableList<DateVO>) {
        dayList=list
        notifyDataSetChanged()
    }
}