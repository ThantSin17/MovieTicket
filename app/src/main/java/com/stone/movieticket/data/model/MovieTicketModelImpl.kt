package com.stone.movieticket.data.model

import android.content.Context
import android.util.Log
import com.stone.movieticket.data.vos.*
import com.stone.movieticket.network.dataagents.MovieTicketDataAgent
import com.stone.movieticket.network.dataagents.RetrofitDataAgentImpl
import com.stone.movieticket.persistance.MovieTicketDatabase

object MovieTicketModelImpl : MovieTicketModel {
    private val mMovieDataAgent: MovieTicketDataAgent = RetrofitDataAgentImpl

    var token: String? = null


    /* Database */
    private var mMovieTicketDatabase: MovieTicketDatabase? = null


    fun initDatabase(context: Context) {

        mMovieTicketDatabase = MovieTicketDatabase.getInstant(context)

    }

    override fun getNowPlayingMovie(
        status: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getNowPlayingMovie(status, onSuccess, onFailure)
    }

    override fun register(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.register(name, email, phone, password, , onFailure)
        mMovieDataAgent.register(
            name = name,
            phone = phone,
            email = email,
            password = password,
            onSuccess = { userInfoResponse ->
                userInfoResponse.data?.let { userVO ->
                    userVO.token = "Bearer ".plus(userInfoResponse.token)
                    mMovieTicketDatabase?.userDao()?.insertUser(userVO)
                    token = userVO.token;
                    onSuccess(userVO)
                }
            },
            onFailure = onFailure
        )
    }

    override fun login(
        email: String,
        password: String,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.login(
            email = email,
            password = password,
            onSuccess = { userInfoResponse ->
                userInfoResponse.data?.let { userVO ->
                    userVO.token = "Bearer ".plus(userInfoResponse.token)
                    mMovieTicketDatabase?.userDao()?.insertUser(userVO)
                    token = userVO.token;
                    onSuccess(userVO)
                }
            },
            onFailure = onFailure
        )
    }

    override fun getProfile(onSuccess: (ProfileVO) -> Unit, onFailure: (String) -> Unit) {
//        mMovieDataAgent.getProfile(onSuccess,onFailure)
    }

    override fun logout(onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.logout(onSuccess, onFailure)
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMovieDetails(movieId, onSuccess, onFailure)
    }

    override fun getCinemaList(
        selectedDate: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit,
        movieId: String
    ) {
        mMovieDataAgent.getCinemaList(selectedDate, movieId, onSuccess, onFailure)
    }

    override fun getMovieSeats(
        timeSlotId: String,
        movieDate: String,
        onSuccess: (List<List<MovieSeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMovieSeats(timeSlotId, movieDate, onSuccess, onFailure)
    }

    override fun getSnacks(onSuccess: (List<SnackVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getSnacks(onSuccess, onFailure)
    }

    override fun getPayments(onSuccess: (List<PaymentVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getPayments(onSuccess, onFailure)
    }

    override fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.createCard(
            cardNumber,
            cardHolder,
            expirationDate,
            cvc,
            onSuccess,
            onFailure
        )
    }

    override fun checkOut(
        extraJson: CheckOutVO,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.checkOut(extraJson, onSuccess, onFailure)
    }


}