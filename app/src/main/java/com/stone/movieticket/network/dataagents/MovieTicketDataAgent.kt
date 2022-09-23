package com.stone.movieticket.network.dataagents

import com.stone.movieticket.data.vos.*
import com.stone.movieticket.network.responses.MovieSeatResponse
import com.stone.movieticket.network.responses.SnackResponse
import com.stone.movieticket.network.responses.UserInfoResponse

interface MovieTicketDataAgent {
    fun getNowPlayingMovie(
        status:String,
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )
    fun register(
        name:String, email:String, phone:String,
        password:String,
        onSuccess:(UserInfoResponse)->Unit,
        onFailure:(String)->Unit
    )
    fun login(
        email:String,
        password: String,
        onSuccess:(UserInfoResponse)->Unit,
        onFailure:(String)->Unit
    )
    fun getProfile(
        token:String,
        onSuccess:(ProfileVO)->Unit,
        onFailure:(String)->Unit
    )
    fun logout(
        token: String,
        onSuccess:(String)->Unit,
        onFailure:(String)->Unit
    )
    fun getMovieDetails(
        movieId:String,
        onSuccess:(MovieVO)->Unit,
        onFailure:(String)->Unit
    )
    fun getCinemaList(
        token: String,
        selectedDate: String,
        movieId: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )
    fun getMovieSeats(
        token: String,
        timeSlotId:String,
        movieDate:String,
        onSuccess: (List<List<MovieSeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    )
    fun getSnacks(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )
    fun getPayments(
        onSuccess: (List<PaymentVO>) -> Unit,
        onFailure: (String) -> Unit
    )
    fun createCard(
        cardNumber:String,
        cardHolder:String,
        expirationDate:String,
        cvc:String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )
    fun checkOut(
        extraJson:CheckOutVO,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    )
}