package com.stone.movieticket.view_holder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.DateVO
import com.stone.movieticket.delegate.ChooseDateDelegate
import kotlinx.android.synthetic.main.activity_choose_movie_time.view.*
import kotlinx.android.synthetic.main.view_holder_day.view.*

class DayListViewHolder(itemView: View, chooseDateDelegate: ChooseDateDelegate) : RecyclerView.ViewHolder(itemView) {
   private var dateVO:DateVO?=null
    fun bindData(dateVO: DateVO) {
        this.dateVO=dateVO;
        if (dateVO.isSelected){
            itemView.tvDayText.apply {
                this.setTextColor(ContextCompat.getColor(
                    itemView.context,
                    R.color.white)
                )
                text=dateVO.date
                textSize=16f
            }
            itemView.tvDateText.apply {
                this.setTextColor(ContextCompat.getColor(
                    itemView.context,
                    R.color.white)

                )
                text=dateVO.day
                this.textSize=16f
            }
        }else{
            itemView.tvDayText.apply {
                this.setTextColor(ContextCompat.getColor(
                    itemView.context,
                    R.color.secondaryTextWhite30Color)
                )

                text=dateVO.date
                this.textSize=14f
            }
            itemView.tvDateText.apply {
                this.setTextColor(ContextCompat.getColor(
                    itemView.context,
                    R.color.secondaryTextWhite30Color)
                )
                text=dateVO.day
                this.textSize=14f
            }
        }

    }

    init {
        itemView.setOnClickListener {

            dateVO?.let { it1 -> chooseDateDelegate.chooseDate(it1.id) }
        }
    }
}