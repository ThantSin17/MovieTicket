package com.stone.movieticket.view_holder

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.PaymentVO
import com.stone.movieticket.delegate.ComboSetDelegate
import kotlinx.android.synthetic.main.view_holder_payment_method.view.*

class PaymentMethodViewHolder(itemView: View, comboSetDelegate: ComboSetDelegate) :
    RecyclerView.ViewHolder(itemView) {
    var paymentVO: PaymentVO? = null
    fun bindData(paymentVO: PaymentVO) {
        this.paymentVO = paymentVO

        if (paymentVO.isSelected) {
            itemView.tvPaymentTitle.apply {
                setTextColor(
                    resources.getColor(R.color.colorPrimary)
//                resources.getColor(R.color.white)
                )
            }
            itemView.tvPaymentDescription.apply {
                setTextColor(resources.getColor(R.color.colorPrimary))
            }
            itemView.ivPayment.apply {
                imageTintList=
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.colorAccent
                    )
                )
            }

        }else {
            itemView.tvPaymentTitle.apply {
                setTextColor(
                    resources.getColor(R.color.secondaryTextBlackColor)
//                resources.getColor(R.color.white)
                )
            }
            itemView.tvPaymentDescription.apply {
                setTextColor(resources.getColor(R.color.secondaryTextBlackColor))
            }

            itemView.ivPayment.apply {
                imageTintList=
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.secondaryTextBlackColor
                        )
                    )
            }

        }
        itemView.tvPaymentTitle.text = paymentVO.name
        itemView.tvPaymentDescription.text = paymentVO.description
    }


    init {
        itemView.setOnClickListener {
            paymentVO?.let {
                comboSetDelegate.onTapPayment(it.id)
            }
        }
    }
}