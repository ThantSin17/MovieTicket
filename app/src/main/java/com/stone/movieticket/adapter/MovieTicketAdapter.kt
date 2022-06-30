package com.stone.movieticket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.view_holder.MovieTicketViewHolder

class MovieTicketAdapter: RecyclerView.Adapter<MovieTicketViewHolder>() {
    val view1=1
    val view2=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTicketViewHolder {
        if (viewType==1) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_ticket, parent, false)
            return MovieTicketViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie_time_item, parent, false)
            return MovieTicketViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: MovieTicketViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 56
    }
    var inn=7
    var ll=0
    override fun getItemViewType(position: Int): Int {

        var list = (position) % (8)

        if (position!=0) {
             ll = (position) % (8)
        }

        if (list==0 || ll-6==1){
            return view2
        }else{
            return view1
        }
    }
}