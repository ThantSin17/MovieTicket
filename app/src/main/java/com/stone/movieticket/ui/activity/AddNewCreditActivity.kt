package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.stone.movieticket.R
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import kotlinx.android.synthetic.main.activity_add_new_credit.*

const val EXTRA_CARD_NUMBER="EXTRA_CARD_NUMBER"
class AddNewCreditActivity : AppCompatActivity() {
    private val mMovieTicketModel:MovieTicketModel =MovieTicketModelImpl

    companion object{
        fun getInstance(context: Context): Intent {
            return Intent(context,AddNewCreditActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_credit)

        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpListener()

    }

    private fun requestData() {
        mMovieTicketModel.createCard(
            cardNumber = tfCardNumber.text.toString(),
            cardHolder = tfCardHolder.text.toString(),
            expirationDate = tfExpired.text.toString(),
            cvc = tfCVC.text.toString(),
            onSuccess = {
                val intent = Intent()
                intent.putExtra(EXTRA_CARD_NUMBER,tfCardNumber.text.toString())
                setResult(RESULT_OK, intent)
                finish()
//                Toast.makeText(applicationContext,"Success", Toast.LENGTH_SHORT).show()
//                onBackPressed()

            },
            onFailure = {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpListener() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
        btnAdd.setOnClickListener {
            if (checkValidation()){
                requestData()
            }


        }
    }

    private fun checkValidation():Boolean {
        var noError=true
        if (tfCardNumber.text.isNullOrEmpty()){
            tfCardNumber.error="Can't empty"
            noError=false
        }else if (tfCardHolder.text.isNullOrEmpty()){
            tfCardHolder.error="Can't empty"
            noError=false
        }
        else if (tfExpired.text.isNullOrEmpty()){
            tfExpired.error="Can't empty"
            noError=false
        }else if (tfCVC.text.isNullOrEmpty()){
            tfCVC.error="Can't empty"
            noError=false
        }
        return noError
    }

}