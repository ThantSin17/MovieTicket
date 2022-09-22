package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.oned.Code128Writer
import com.google.zxing.qrcode.QRCodeWriter
import com.stone.movieticket.R
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import com.stone.movieticket.data.vos.CheckOutVO
import com.stone.movieticket.data.vos.MovieVO
import com.stone.movieticket.network.MovieTicketApi
import com.stone.movieticket.utils.BASE_IMG_URL
import kotlinx.android.synthetic.main.activity_awesome.*
import kotlinx.android.synthetic.main.activity_awesome.ivBack
import kotlinx.android.synthetic.main.activity_awesome.ivMovie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class AwesomeActivity : AppCompatActivity() {

    private lateinit var mCheckOutVO: CheckOutVO
    private val movieTicketModel: MovieTicketModel = MovieTicketModelImpl

    companion object {
        private const val EXTRA_CHECKOUT_VO = "EXTRA_CHECKOUT_VO"
        fun getInstance(
            context: Context,
            checkOutVO: CheckOutVO,

        ): Intent {
            val intent = Intent(context, AwesomeActivity::class.java)
            intent.putExtra(EXTRA_CHECKOUT_VO, checkOutVO)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_awesome)


        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpListener()

        //extra
        mCheckOutVO = intent?.getSerializableExtra(EXTRA_CHECKOUT_VO) as CheckOutVO


        bindData()
    }


    private fun bindData() {


        Glide.with(this)
            .load("$BASE_IMG_URL${mCheckOutVO.moviePoster}")
            .into(ivMovie)
        tvMovieTitle.text = mCheckOutVO.movieName
        tvMovieDuration.text = "${mCheckOutVO.duration} M"
        tvCinemaName.text= mCheckOutVO.cinema
        tvBookingNo.text = mCheckOutVO.bookingNo
        tvShowTime.text = mCheckOutVO.bookingDate
        tvShowTime.text= "${mCheckOutVO.timeslot?.startTime} - ${mCheckOutVO.getFormatDateTime()}"
        tvRow.text = mCheckOutVO.row
        tvTheater.text=mCheckOutVO.cinema
        tvSeats.text = mCheckOutVO.seat
        tvPrice.text = mCheckOutVO.total
        ivBar.setImageBitmap(generateBarcode())

    }

    private fun generateBarcode() :Bitmap{
        val width=900
        val height=300//pixels
        val qrCodeContent = mCheckOutVO.bookingNo
//        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val bits = Code128Writer().encode(qrCodeContent, BarcodeFormat.CODE_128, width, height)
        return Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565).also {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    private fun setUpListener() {
        ivBack.setOnClickListener {
            startActivity(MainActivity.getInstance(this))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(MainActivity.getInstance(this))
        finish()
    }

}