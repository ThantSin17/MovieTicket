package com.stone.movieticket.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.data.vos.SnackVO
import com.stone.movieticket.delegate.ComboSetDelegate
import kotlinx.android.synthetic.main.view_holder_combo_set.view.*

class ComboSetViewHolder(itemView: View, comboSetDelegate: ComboSetDelegate) : RecyclerView.ViewHolder(itemView) {
    private var snackVO: SnackVO? = null

    fun bindData(snackVO: SnackVO) {
        this.snackVO = snackVO
        itemView.value.text=snackVO.quantity.toString()
        itemView.tvComboSetTitle.text = snackVO.name
        itemView.tvComboSetDescription.text = snackVO.description
        itemView.tvComboSetAmount.text = "${snackVO.price} $"
    }
    init {
        val currentValue=itemView.value.text.toString()
        itemView.remove.setOnClickListener {

            snackVO?.let {
            comboSetDelegate.onTapRemoveSnack(it.id,Integer.parseInt(currentValue))
                }
        }
        itemView.plus.setOnClickListener {
            snackVO?.let {
                comboSetDelegate.onTapPlusSnack(it.id, Integer.parseInt(currentValue))
            }
        }
    }

}