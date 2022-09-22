package com.stone.movieticket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.PaymentVO
import com.stone.movieticket.delegate.ComboSetDelegate
import com.stone.movieticket.view_holder.PaymentMethodViewHolder

class PaymentMethodAdapter(private val comboSetDelegate: ComboSetDelegate):RecyclerView.Adapter<PaymentMethodViewHolder>() {
    private var paymentList:List<PaymentVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_payment_method,parent,false)
        return PaymentMethodViewHolder(view,comboSetDelegate)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        if (paymentList.isNotEmpty()){
            holder.bindData(paymentList[position])
        }
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(paymentList: List<PaymentVO>) {
        this.paymentList=paymentList
        notifyDataSetChanged()
    }
}