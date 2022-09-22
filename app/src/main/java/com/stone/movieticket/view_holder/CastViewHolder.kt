package com.stone.movieticket.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.CastVO
import com.stone.movieticket.utils.BASE_IMG_URL
import kotlinx.android.synthetic.main.view_holder_cast.view.*

class CastViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    fun bindData(castVO: CastVO) {

        Glide.with(itemView.context)
            .load("$BASE_IMG_URL${castVO.profilePath}")
            .apply(
                RequestOptions()
                .placeholder(R.drawable.ic_baseline_person_24)
            )
            .into(itemView.ivCast)
    }
}