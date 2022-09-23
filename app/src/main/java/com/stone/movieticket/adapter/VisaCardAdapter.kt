package com.stone.movieticket.adapter

import alirezat775.lib.carouselview.CarouselAdapter
import android.annotation.SuppressLint
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.CardVO
import kotlinx.android.synthetic.main.view_holder_visa.view.*

class VisaCardAdapter :CarouselAdapter(){

    private var cardList:List<CardVO> = arrayListOf()
    private var viewHolder:CarouselViewHolder? =null
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        viewHolder=holder
        if (cardList.isNotEmpty()){
            (holder as VisaCardViewHolder).bindData(cardList[position])
        }else {
            (holder as VisaCardViewHolder).bindNoData()
            Log.i("Gooo","nodatabind")
        }
        Log.i("Gooo","nodatabind")
    }

    override fun getItemCount(): Int {
        return if (cardList.isEmpty()) 1 else cardList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_visa,parent,false)
        return  VisaCardViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cards: List<CardVO>?) {
        if (cards != null) {
            cardList=cards
            notifyDataSetChanged()
        }
    }

    inner class VisaCardViewHolder(itemView: View) : CarouselViewHolder(itemView) {

        fun bindNoData(){
            itemView.noCard.visibility=View.VISIBLE
            itemView.rlVisaCard.visibility=View.GONE
        }
        fun  bindData(cardVO: CardVO){

            itemView.noCard.visibility=View.GONE
            itemView.rlVisaCard.visibility=View.VISIBLE

            itemView.tvFourthSlot.text=cardVO.getFourthSlot()
            itemView.tvCardHolderName.text=cardVO.cardHolder
            itemView.tvExpiredDate.text=cardVO.expirationDate


        }
    }
}

