package com.stone.movieticket.delegate

import com.stone.movieticket.data.vos.ProfileVO

interface LoginDelegate {
    fun onTapConfirm()
}
interface RegisterDelegate{
    fun doRegister(name:String,email:String,phone:String,password:String)
    fun doLogin(email:String,password:String)

}
