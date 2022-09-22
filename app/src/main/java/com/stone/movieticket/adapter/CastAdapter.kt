package com.stone.movieticket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.CastVO
import com.stone.movieticket.view_holder.CastViewHolder
import com.stone.movieticket.view_holder.MovieViewHolder

class CastAdapter:RecyclerView.Adapter<CastViewHolder>() {
    private var casts:List<CastVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_cast,parent,false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        if (casts.isNotEmpty()){
            holder.bindData(casts[position])
        }
    }

    override fun getItemCount(): Int {
        return casts.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(casts: List<CastVO>?) {
        if (casts != null) {
            this.casts=casts
            notifyDataSetChanged()
        }
    }
}