package com.stone.movieticket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.view_holder.ComboSetViewHolder

class ComboSetAdapter: RecyclerView.Adapter<ComboSetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComboSetViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_combo_set,parent,false)
        return ComboSetViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComboSetViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}