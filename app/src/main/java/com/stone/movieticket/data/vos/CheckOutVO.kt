package com.stone.movieticket.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat

data class CheckOutVO(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("booking_no")
    val bookingNo:String?,
    @SerializedName("booking_date")
    val bookingDate:String?,
    @SerializedName("row")
    val row:String?,
    @SerializedName("seat")
    val seat:String?,
    @SerializedName("total_seat")
    val totalSeat:Int?,
    @SerializedName("total")
    val total:String?,
    @SerializedName("movie_id")
    val movie_id:String?,
    @SerializedName("cinema_id")
    val cinemaId:String?,
    @SerializedName("username")
    val username:String?,
    @SerializedName("timeslot")
    val timeslot:TimeslotVO?,
    @SerializedName("snacks")
    val snacks:List<SnackVO>?,
    @SerializedName("qr_code")
    val qr_code:String?,
    @SerializedName("seat_number")
    val seatNumber:String?,
    @SerializedName("card_id")
    val cardId:String?,
    @SerializedName("cinema_day_timeslot_id")
    val timeslotId:String?,
    @SerializedName("cinema")
    var cinema:String?,
    @SerializedName("moviePoster")
    var moviePoster:String?,
    @SerializedName("movieName")
    var movieName:String?,
    @SerializedName("duration")
    var duration:String?,










    ):Serializable {

    fun getFormatDateTime(): String {
        val formatter = SimpleDateFormat("yyyy-mm-dd")
        val text = "2022-01-06"
        val date = formatter.parse(text)
        val reformat = SimpleDateFormat("dd MMM")
        return reformat.format(date)
    }
}