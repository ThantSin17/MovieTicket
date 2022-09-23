package com.stone.movieticket.network.dataagents

import android.util.Log
import com.google.gson.Gson
import com.stone.movieticket.data.vos.*
import com.stone.movieticket.network.MovieTicketApi
import com.stone.movieticket.network.responses.*
import com.stone.movieticket.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : MovieTicketDataAgent {

    private var mMovieTicketApi: MovieTicketApi? = null

    override fun register(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (UserInfoResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.registerWithEmail(
            name = name,
            email = email,
            phone = phone,
            password = password
        )?.enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(
                call: Call<UserInfoResponse>,
                response: Response<UserInfoResponse>
            ) {

                Log.i("Gooo", Gson().toJson(response.errorBody()))
                if (response.isSuccessful) {
                    if (response.code() == 201) {
                        response.body()?.let { onSuccess(it) }
                    } else {
                        onFailure(response.body()?.message ?: "unknown error")
                    }
                } else {
                    onFailure(response.body()?.message ?: "unknown error")
                }

            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
//                onFailure(t.message.toString())
            }
        })
    }

    override fun login(
        email: String,
        password: String,
        onSuccess: (UserInfoResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.loginWithEmail(email, password)
            ?.enqueue(object : Callback<UserInfoResponse> {
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            response.body()?.let {
                                onSuccess(it)
                            }
                            response.body()?.token?.let {
//                            token=it
                            }
                        } else {

                            onFailure(response.message())
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    onFailure(t.message.toString())
                }
            })
    }


    override fun getNowPlayingMovie(
        status: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.getNowShowingMovie(status = status)?.enqueue(
            object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { onSuccess(it) }
//                        Log.i("Goooo", token)
                    } else onFailure(response.message())
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message.toString())
                }
            }
        )
    }


    override fun getProfile(
        token: String,
        onSuccess: (ProfileVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.getProfile(token = token)?.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            onSuccess(it)
                        }
                    } else {

                        onFailure(response.message())
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                onFailure(t.message.toString())
            }
        })
    }

    override fun logout(token: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        mMovieTicketApi?.logout(token = token)?.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        response.body()?.message?.let {
                            onSuccess(it)
                        }
                    } else {

                        onFailure(response.message())
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                onFailure(t.message.toString())
            }
        })
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.getMovieDetailById(movieId = movieId)?.enqueue(
            object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { onSuccess(it) }
//                        Log.i("Goooo", token)
                    } else onFailure(response.message())
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    onFailure(t.message.toString())
                }
            }
        )
    }

    override fun getCinemaList(
        token: String,
        selectedDate: String,
        movieId: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.getTimeSlotByDate(
            token = token,
            date = selectedDate,
            movieId = movieId
        )
            ?.enqueue(
                object : Callback<CinemaResponse> {
                    override fun onResponse(
                        call: Call<CinemaResponse>,
                        response: Response<CinemaResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.data?.let { onSuccess(it) }
//                        Log.i("Goooo", token)
                        } else onFailure(response.message())
                    }

                    override fun onFailure(call: Call<CinemaResponse>, t: Throwable) {
                        onFailure(t.message.toString())
                    }
                }
            )
    }

    override fun getMovieSeats(
        token: String,
        timeSlotId: String,
        movieDate: String,
        onSuccess: (List<List<MovieSeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.getMovieSeats(
            token = token,
            timeSlotId = timeSlotId,
            bookingDate = movieDate
        )?.enqueue(
            object : Callback<MovieSeatResponse> {
                override fun onResponse(
                    call: Call<MovieSeatResponse>,
                    response: Response<MovieSeatResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { onSuccess(it) }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieSeatResponse>, t: Throwable) {
                    onFailure(t.message.toString())
                }
            }
        )
    }

    override fun getSnacks(onSuccess: (List<SnackVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieTicketApi?.getSnacks()?.enqueue(
            object : Callback<SnackResponse> {
                override fun onResponse(
                    call: Call<SnackResponse>,
                    response: Response<SnackResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { onSuccess(it) }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<SnackResponse>, t: Throwable) {
                    onFailure(t.message.toString())
                }
            }
        )
    }

    override fun getPayments(
        onSuccess: (List<PaymentVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.getPaymentMethod()?.enqueue(
            object : Callback<PaymentResponse> {
                override fun onResponse(
                    call: Call<PaymentResponse>,
                    response: Response<PaymentResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { onSuccess(it) }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                    onFailure(t.message.toString())
                }
            }
        )
    }

    override fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.createCard(cardNumber, cardHolder, expirationDate, cvc)?.enqueue(
            object : Callback<CardResponse> {
                override fun onResponse(
                    call: Call<CardResponse>,
                    response: Response<CardResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { onSuccess(it) }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CardResponse>, t: Throwable) {
                    onFailure(t.message.toString())
                }
            }
        )
    }

    override fun checkOut(
        extraJson: CheckOutVO,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieTicketApi?.checkOut(checkOut = extraJson)
            ?.enqueue(object : Callback<CheckOutResponse> {
                override fun onResponse(
                    call: Call<CheckOutResponse>,
                    response: Response<CheckOutResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.code == 200) {
                            response.body()?.data?.let { onSuccess(it) }
                        } else response.body()?.message?.let { onFailure(it) }
                    } else response.body()?.message?.let { onFailure(it) }
                }

                override fun onFailure(call: Call<CheckOutResponse>, t: Throwable) {
                    Log.i("Gooo", t.stackTraceToString())
                    onFailure(t.message.toString())
                }
            })
    }
//
//    fun isResponseOK():Boolean{
//        return  true
//    }

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val interceptor = Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.header("Accept", "application/json")
            requestBuilder.header("Cache-control", "no-cache")
//            if (token.isNotEmpty()){
//                requestBuilder.header("Authorization", "Bearer $token")
//            }
            chain.proceed(requestBuilder.build())
        }
        val mClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mMovieTicketApi = retrofit.create(MovieTicketApi::class.java)


    }


}