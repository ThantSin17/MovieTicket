package com.stone.movieticket.data.model

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.stone.movieticket.data.vos.*
import com.stone.movieticket.network.dataagents.MovieTicketDataAgent
import com.stone.movieticket.network.dataagents.RetrofitDataAgentImpl
import com.stone.movieticket.persistance.MovieTicketDatabase

object MovieTicketModelImpl : MovieTicketModel {
    private val mMovieDataAgent: MovieTicketDataAgent = RetrofitDataAgentImpl

    var token: String? = null


    /* Database */
    var mMovieTicketDatabase: MovieTicketDatabase? = null


    fun initDatabase(context: Context) {

        mMovieTicketDatabase = MovieTicketDatabase.getInstant(context)
        token = mMovieTicketDatabase?.userDao()?.getToken()
        Log.i("Gooo", token.toString())
    }

    override fun getNowPlayingMovie(
        status: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMovieTicketDatabase?.movieDao()?.getMoviesByStatus(status) ?: listOf())
        mMovieDataAgent.getNowPlayingMovie(
            status = status,
            onSuccess = { movieList ->
                movieList.forEach {
                    it.type = status
                    mMovieTicketDatabase?.movieDao()?.insertSingleMovie(it)
                }
                onSuccess(movieList)
            },
            onFailure = onFailure
        )
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
                    token = userVO.token
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
                Log.i("GoooToken", userInfoResponse.token.toString())
                userInfoResponse.data?.let { userVO ->

                    userVO.token = "Bearer ".plus(userInfoResponse.token)
                    mMovieTicketDatabase?.userDao()?.insertUser(userVO)
                    token = userVO.token
                    onSuccess(userVO)
                }

            },
            onFailure = onFailure
        )
    }

    override fun getProfile(onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit) {
//        mMovieDataAgent.getProfile(onSuccess,onFailure)
        try {
            mMovieTicketDatabase?.userDao()?.getUser()?.let { onSuccess(it) }
        } catch (e: Exception) {
            onFailure
        }

    }

    override fun logout(onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
//        mMovieDataAgent.logout(onSuccess, onFailure)
        token?.let {
//            mMovieTicketDatabase.userDao().deleteUser()
            mMovieDataAgent.logout(
                token = it,
                onSuccess = { message ->
                    token = null
                    mMovieTicketDatabase?.userDao()?.deleteUser()
                    onSuccess(message)
                },
                onFailure = onFailure
            )
        }
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val movieFromDB: MovieVO? = mMovieTicketDatabase?.movieDao()?.getMovieById(movieId.toInt())
        movieFromDB?.let(onSuccess)
        mMovieDataAgent.getMovieDetails(
            movieId = movieId,
            onSuccess = { movieVO ->
                movieFromDB?.let {
                    movieVO.type = it.type
                }
                mMovieTicketDatabase?.movieDao()?.insertSingleMovie(movieVO)
                onSuccess(movieVO)
            },
            onFailure = onFailure
        )
    }

    override fun getCinemaList(
        selectedDate: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit,
        movieId: String
    ) {
        token?.let {
            val dateVo =
                mMovieTicketDatabase?.dateAndCinemaDao()?.getCinemaByDate(date = selectedDate)

//            dateVo?.cinema?.let { it1 -> onSuccess(it1) }
            onSuccess(dateVo?.cinema ?: listOf())

            Log.i("Goooo", Gson().toJson(dateVo))

            mMovieDataAgent.getCinemaList(
                token = it,
                selectedDate = selectedDate,
                movieId = movieId,
                onSuccess = {
                    val dateAndCinemaVO = DateAndCinemaVO(
                        date = selectedDate,
                        cinema = it
                    )
                    try {
                        mMovieTicketDatabase?.dateAndCinemaDao()
                            ?.insertDateAndCinema(dateAndCinemaVO)
                    } catch (e: Exception) {
                        Log.i("Goooo", e.toString())
                    }


                    onSuccess(it)
                },
                onFailure = onFailure
            )
        }
    }

    override fun getMovieSeats(
        timeSlotId: String,
        movieDate: String,
        onSuccess: (List<List<MovieSeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        token?.let {
            mMovieDataAgent.getMovieSeats(
                token = it,
                timeSlotId = timeSlotId,
                movieDate = movieDate,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun getSnacks(onSuccess: (List<SnackVO>) -> Unit, onFailure: (String) -> Unit) {
        token?.let {
            onSuccess(mMovieTicketDatabase?.snackDao()?.getAllSnacks() ?: listOf())
            mMovieDataAgent.getSnacks(
                token = it,
                onSuccess = { snacks ->
                    mMovieTicketDatabase?.snackDao()?.insertSnackList(snacks)
                    onSuccess(snacks)
                },
                onFailure = onFailure
            )
        }
    }

    override fun getPayments(onSuccess: (List<PaymentVO>) -> Unit, onFailure: (String) -> Unit) {
        token?.let { it ->
            onSuccess(mMovieTicketDatabase?.paymentMethodDao()?.getAllPaymentMethods() ?: listOf())
            mMovieDataAgent.getPayments(
                token = it,
                onSuccess = { payments ->
                    mMovieTicketDatabase?.paymentMethodDao()?.insertPaymentMethodList(payments)
                    onSuccess(payments)
                },
                onFailure = onFailure
            )
        }
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