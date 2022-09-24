package com.stone.movieticket.ui.activity

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselView
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowInsetsControllerCompat
import com.google.gson.Gson
import com.stone.movieticket.R
import com.stone.movieticket.adapter.VisaCardAdapter
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import com.stone.movieticket.data.vos.*
import kotlinx.android.synthetic.main.activity_choose_payment.*
import org.json.JSONObject

class ChoosePaymentActivity : AppCompatActivity() {
    private val mMovieTicketModel: MovieTicketModel = MovieTicketModelImpl
    lateinit var mVisaCardAdapter: VisaCardAdapter
    private val cardList: MutableList<CardVO> = mutableListOf()
    private lateinit var carousel: Carousel
    private var cardId = ""
    private lateinit var snacks: String
    private var totalAmount: Int = 0

    //Extra

    private lateinit var extraJson:JSONObject

    companion object {
        private const val EXTRA_JSON = "EXTRA_JSON"
        private const val EXTRA_PRICE = "EXTRA_PRICE"

        fun getInstance(context: Context, extraJson: String, totalPrice: Int): Intent {
            val intent = Intent(context, ChoosePaymentActivity::class.java)
            intent.putExtra(EXTRA_JSON, extraJson)
            intent.putExtra(EXTRA_PRICE,totalPrice)
            return intent
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
        requestData()

        //extra
        try {
            val extra=intent?.getStringExtra(EXTRA_JSON)
            extraJson= JSONObject("""$extra""")
            totalAmount= intent?.getIntExtra(EXTRA_PRICE,0) ?: 0

            tvPaymentAmount.text = "$ $totalAmount"
        }catch (e:Exception){
            Log.i("Goooo",e.toString())
        }


    }

    override fun onResume() {
        super.onResume()

    }

    private fun requestData() {
        mMovieTicketModel.getCards(
            onSuccess = { cards ->
                cardList.clear()
                cards.let { cardList.addAll(it) }
                cardList.reverse()
                mVisaCardAdapter.setNewData(cardList)
                carousel.setCurrentPosition(0)
            },
            onFail = {}
        )
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val cardNumber = data?.getStringExtra(EXTRA_CARD_NUMBER)
//                cardList.forEachIndexed {index,card->
//                    if (Integer.parseInt(cardNumber)==Integer.parseInt(card.cardNumber)) {
//                        Log.i("Goooo",cardNumber.toString())
//                        Log.i("Goooo",index.toString())
//                        carousel.setCurrentPosition(index)
//                    }
//
//                }
                requestData()

            }
        }

    private fun setUpListener() {
        tvAddNewCardLabel.setOnClickListener {
            resultLauncher.launch(AddNewCreditActivity.getInstance(this))
        }
        ivBack.setOnClickListener {
            onBackPressed()
        }
        btnPay.setOnClickListener {
            val cardId= cardList[carousel.getCurrentPosition()].id
            extraJson.put("card_id",cardId)

            val mCheckOutVO=Gson().fromJson(extraJson.toString(),CheckOutVO::class.java)

            mMovieTicketModel.checkOut(
                extraJson = mCheckOutVO,
                onSuccess = {
                    var returnCheckOut=it
                    returnCheckOut.movieName=mCheckOutVO.movieName
                    returnCheckOut.duration=mCheckOutVO.duration
                    returnCheckOut.cinema=mCheckOutVO.cinema
                    returnCheckOut.moviePoster=mCheckOutVO.moviePoster
                    Log.i("Gooo",returnCheckOut.toString())
                    Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
                    startActivity(AwesomeActivity.getInstance(this,returnCheckOut))
                },
                onFailure = {
                    Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                },
            )
//
        }

    }


    private fun setUpRecyclerView() {
        mVisaCardAdapter = VisaCardAdapter()
        carousel = Carousel(this, rvVisaCard, mVisaCardAdapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.scaleView(true)

    }
}