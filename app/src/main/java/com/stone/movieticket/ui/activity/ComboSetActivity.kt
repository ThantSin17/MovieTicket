package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.movieticket.R
import com.stone.movieticket.adapter.ComboSetAdapter
import com.stone.movieticket.adapter.PaymentMethodAdapter
import kotlinx.android.synthetic.main.activity_combo_set.*
import kotlinx.android.synthetic.main.view_holder_combo_set.view.*

class ComboSetActivity : AppCompatActivity() {
    lateinit var mComSetAdapter: ComboSetAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, ComboSetActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combo_set)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpRecyclerView()
        setUpListener()
    }

    private fun setUpListener() {
        btnPay.setOnClickListener {
            startActivity(ChoosePaymentActivity.getInstance(this))
        }
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        mComSetAdapter = ComboSetAdapter()
        rvComboSet.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvComboSet.adapter = mComSetAdapter

        mPaymentMethodAdapter = PaymentMethodAdapter()
        rvPaymentMethod.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvPaymentMethod.adapter = mPaymentMethodAdapter
    }
}