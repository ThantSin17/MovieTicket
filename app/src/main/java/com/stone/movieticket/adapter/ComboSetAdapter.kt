package com.stone.movieticket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.SnackVO
import com.stone.movieticket.delegate.ComboSetDelegate
import com.stone.movieticket.view_holder.ComboSetViewHolder

class ComboSetAdapter(private val comboSetDelegate: ComboSetDelegate): RecyclerView.Adapter<ComboSetViewHolder>() {
    private var comboSetList:List<SnackVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComboSetViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_combo_set,parent,false)
        return ComboSetViewHolder(view,comboSetDelegate)
    }

    override fun onBindViewHolder(holder: ComboSetViewHolder, position: Int) {
        if(comboSetList.isNotEmpty()){
            holder.bindData(comboSetList[position])
        }
    }

    override fun getItemCount(): Int {
        return comboSetList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(comboSets: List<SnackVO>) {
        this.comboSetList=comboSets
        notifyDataSetChanged()
    }
}