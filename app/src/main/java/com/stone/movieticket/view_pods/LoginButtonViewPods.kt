package com.stone.movieticket.view_pods

import android.R
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.stone.movieticket.delegate.LoginDelegate
import kotlinx.android.synthetic.main.view_pod_login_btn.view.*


class LoginButtonViewPods @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    fun setUpButtonViewPod(buttonName: CharSequence){
        btnRegister.text=buttonName
    }
    fun onClickRegisterButton(listener:LoginDelegate){
        btnRegister.setOnClickListener {
            listener.onTapConfirm()
        }
    }


}