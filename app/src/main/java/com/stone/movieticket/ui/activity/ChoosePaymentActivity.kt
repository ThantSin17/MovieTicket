package com.stone.movieticket.ui.activity

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselView
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowInsetsControllerCompat
import com.stone.movieticket.R
import com.stone.movieticket.adapter.VisaCardAdapter
import kotlinx.android.synthetic.main.activity_choose_payment.*

class ChoosePaymentActivity : AppCompatActivity() {
    lateinit var mVisaCardAdapter: VisaCardAdapter
    companion object{
        fun getInstance(context:Context):Intent{
            return Intent(context,ChoosePaymentActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_payment)

        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpRecyclerView()
        setUpListener()
    }

    private fun setUpListener() {
        tvAddNewCardLabel.setOnClickListener{
            startActivity(AddNewCreditActivity.getInstance(this))
        }
        ivBack.setOnClickListener{
            onBackPressed()
        }
        btnPay.setOnClickListener {
            startActivity(AwesomeActivity.getInstance(this))
        }
    }

    private fun setUpRecyclerView() {
        mVisaCardAdapter = VisaCardAdapter()
        val carousel = Carousel(this, rvVisaCard, mVisaCardAdapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.scaleView(true)
    }
}