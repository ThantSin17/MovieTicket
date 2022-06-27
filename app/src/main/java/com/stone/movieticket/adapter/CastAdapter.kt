package com.stone.movieticket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.view_holder.CastViewHolder
import com.stone.movieticket.view_holder.MovieViewHolder

class CastAdapter:RecyclerView.Adapter<CastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_cast,parent,false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}