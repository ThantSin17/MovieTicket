package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.stone.movieticket.R
import com.stone.movieticket.adapter.ComboSetAdapter
import com.stone.movieticket.adapter.PaymentMethodAdapter
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import com.stone.movieticket.data.vos.PaymentVO
import com.stone.movieticket.data.vos.SnackVO
import com.stone.movieticket.delegate.ComboSetDelegate
import kotlinx.android.synthetic.main.activity_combo_set.*
import org.json.JSONArray
import org.json.JSONObject

class ComboSetActivity : AppCompatActivity(), ComboSetDelegate {
    lateinit var mComSetAdapter: ComboSetAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter


    private val movieTicketModel: MovieTicketModel = MovieTicketModelImpl

    private var paymentList: MutableList<PaymentVO> = mutableListOf()
    private val snackList: MutableList<SnackVO> = mutableListOf()
    private var subTotal = 0
//    private var totalAmount =0

    //extra
    private lateinit var extraJson:JSONObject
    private var extraPrice=0

    companion object {
        private const val EXTRA_JSON = "EXTRA_JSON"
        private const val EXTRA_TICKET_PRICE = "EXTRA_TICKET_PRICE"
        fun getInstance(context: Context, jsonObj: String, ticketPrice: Int): Intent {
            val intent= Intent(context, ComboSetActivity::class.java)
            intent.putExtra(EXTRA_JSON,jsonObj)
            intent.putExtra(EXTRA_TICKET_PRICE,ticketPrice)
            return  intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combo_set)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.setDecorFitsSystemWindows(false)
//        } else {
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//            )
//        }
        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpRecyclerView()
        setUpListener()

        //extra
        extraPrice=intent?.getIntExtra(EXTRA_TICKET_PRICE,0) ?: 0
        btnPay.text="Pay $$extraPrice"
        try {
            val extra =intent?.getStringExtra(EXTRA_JSON).toString()
            extraJson= JSONObject("""$extra""")
        }catch (e:Exception){
            Log.i("Gooo",e.toString())
        }


        requestData()
    }

    private fun requestData() {
        movieTicketModel.getSnacks(
            onSuccess = {
                bindComboSetData(it)
            },
            onFailure = {
                showMessage(it)
            }
        )
        movieTicketModel.getPayments(
            onSuccess = {
                paymentList.clear()
                paymentList.addAll(it)
                mPaymentMethodAdapter.setNewData(it)
            },
            onFailure = {
                showMessage(it)
            }
        )
    }

    private fun bindComboSetData(comboSets: List<SnackVO>) {
        snackList.clear()
        snackList.addAll(comboSets)
        mComSetAdapter.setNewData(comboSets)
    }

    private fun setUpListener() {
        btnPay.setOnClickListener {
//            snackList.removeIf { it.quantity==0 }

            val snacks=JSONArray("""${Gson().toJson(snackList)}""")
            extraJson.put("total_price",extraPrice)
            extraJson.put("snacks",snacks)

            startActivity(ChoosePaymentActivity.getInstance(this,extraJson.toString(),totalPrice=extraPrice))
        }
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        mComSetAdapter = ComboSetAdapter(this)
        rvComboSet.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvComboSet.adapter = mComSetAdapter

        mPaymentMethodAdapter = PaymentMethodAdapter(this)
        rvPaymentMethod.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPaymentMethod.adapter = mPaymentMethodAdapter
    }

    private fun showMessage(e: String) {
        Toast.makeText(applicationContext, e, Toast.LENGTH_SHORT).show()
    }

    override fun onTapPayment(id: Int) {
        for (paymentVo in paymentList){
            paymentVo.isSelected = paymentVo.id==id
        }
        mPaymentMethodAdapter.setNewData(paymentList)
    }

    override fun onTapPlusSnack(id: Int, currentValue: Int) {
       for (snackVo in snackList){
           if (snackVo.id==id){
               snackVo.quantity+=1
//               var addAmount=snackVo.currentValue * snackVo.price
               subTotal +=snackVo.price
               extraPrice+=snackVo.price
           }
       }
        tvTotalAmount.text="Sub Total : $subTotal\$"
        btnPay.text="Pay $$extraPrice"
        mComSetAdapter.setNewData(snackList)
    }

    override fun onTapRemoveSnack(id: Int, currentValue: Int) {
        for (snackVo in snackList){
            if (snackVo.id==id && snackVo.quantity!=0){
                snackVo.quantity--
                subTotal-=snackVo.price
                extraPrice-=snackVo.price
            }
        }
        tvTotalAmount.text="Sub Total : $subTotal\$"
        btnPay.text="Pay $$extraPrice"
        mComSetAdapter.setNewData(snackList)
    }


}