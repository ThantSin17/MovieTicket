package com.stone.movieticket.data.model

import com.stone.movieticket.data.vos.*

interface MovieTicketModel {
    fun getNowPlayingMovie(
        status:String,
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )
    fun register(
        name:String,email:String,phone:String,
        password:String,
        onSuccess:(ProfileVO)->Unit,
        onFailure:(String)->Unit
    )
    fun login(
        email:String,
        password: String,
        onSuccess:(ProfileVO)->Unit,
        onFailure:(String)->Unit
    )
    fun getProfile(
        onSuccess:(ProfileVO)->Unit,
        onFailure:(String)->Unit
    )
    fun logout(
        onSuccess:(String)->Unit,
        onFailure:(String)->Unit
    )
    fun getMovieDetails(
        movieId:String,
        onSuccess:(MovieVO)->Unit,
        onFailure:(String)->Unit
    )
    fun getCinemaList(
        selectedDate: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit,
        movieId: String
    )
    fun getMovieSeats(
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