package com.stone.movieticket.delegate

interface ComboSetDelegate {
    fun onTapPayment(id:Int)
    fun onTapPlusSnack(id: Int,currentValue:Int)
    fun onTapRemoveSnack(id: Int,currentValue:Int)
}