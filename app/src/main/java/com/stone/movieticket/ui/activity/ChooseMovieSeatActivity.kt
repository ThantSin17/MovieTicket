package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.stone.movieticket.R
import com.stone.movieticket.adapter.MovieSeatAdapter
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import com.stone.movieticket.data.vos.*
import com.stone.movieticket.delegate.MovieSeatsDelegate
import kotlinx.android.synthetic.main.activity_choose_movie_seats_activity.*
import org.json.JSONObject


//"${dateVO.day},${dateVO.date},${timeslotVO.startTime}"
class ChooseMovieSeatActivity : AppCompatActivity(), MovieSeatsDelegate {
    lateinit var mMovieTicketAdapter: MovieSeatAdapter
    private lateinit var movieVO: MovieVO
    private lateinit var cinemaVO: CinemaVO
    private lateinit var dateVO: DateVO
    private lateinit var timeslotVO: TimeslotVO
    var selectedSeats = ""
    var ticketPrice=0
    private val mMovieTicketModel: MovieTicketModel = MovieTicketModelImpl
    private val movieSeats: MutableList<MovieSeatVO> = mutableListOf()
    private val selectedMovieSeats: MutableList<MovieSeatVO> = mutableListOf()

    companion object {
        private const val EXTRA_MOVIE_VO = "EXTRA_MOVIE_NAME"
        private const val EXTRA_DATE_VO = "EXTRA_MOVIE_DATE"
        private const val EXTRA_CINEMA_VO = "EXTRA_CINEMA"
        private const val EXTRA_TIMESLOT_VO = "EXTRA_TIMESLOT"
        fun getInstance(
            context: Context,
            timeslotVO: TimeslotVO,
            movieVO: MovieVO,
            dateVO: DateVO,
            cinemaVO: CinemaVO
        ): Intent {

            val intent = Intent(context, ChooseMovieSeatActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_VO, movieVO)
            intent.putExtra(EXTRA_CINEMA_VO, cinemaVO)
            intent.putExtra(EXTRA_DATE_VO, dateVO)
            intent.putExtra(EXTRA_TIMESLOT_VO, timeslotVO)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_movie_seats_activity)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpRecyclerView()
        setUpListener()
        movieVO = intent?.getSerializableExtra(EXTRA_MOVIE_VO) as MovieVO
        tvMovieTime.text = movieVO.originalTitle
        cinemaVO = intent?.getSerializableExtra(EXTRA_CINEMA_VO) as CinemaVO
        tvCinemaName.text = cinemaVO.cinema
        dateVO = intent?.getSerializableExtra(EXTRA_DATE_VO) as DateVO
        timeslotVO = intent?.getSerializableExtra(EXTRA_TIMESLOT_VO) as TimeslotVO

        if (dateVO != null && timeslotVO != null) {
            tvMovieTime.text = "${dateVO.day},${dateVO.date},${timeslotVO.startTime}"
            requestData()
        }
    }

    private fun requestData() {

        mMovieTicketModel.getMovieSeats(
            movieDate = dateVO.time,
            timeSlotId = timeslotVO.cinemaDayTimeslotId.toString(),
            onSuccess = {
                bindMovieSeatData(it)
            },
            onFailure = {}
        )
    }

    private fun bindMovieSeatData(movieSeatList: List<List<MovieSeatVO>>) {
        movieSeats.clear()
        for (movieSeat in movieSeatList) {
            for (seat in movieSeat) {
                movieSeats.add(seat)
            }
        }
        mMovieTicketAdapter.setNewData(movieSeats)

    }

    private fun setUpListener() {
        ivBack.setOnClickListener { onBackPressed() }
        btnBuyTicket.setOnClickListener {
            if (selectedSeats.isNotEmpty()){
                val obj=JSONObject()
                obj.put("cinema_day_timeslot_id",timeslotVO.cinemaDayTimeslotId)
                obj.put("seat_number",selectedSeats.dropLast(1))
                obj.put("booking_date",dateVO.time)
                obj.put("movie_id",movieVO.id)
                obj.put("cinema_id",cinemaVO.cinemaId)
                obj.put("cinema",cinemaVO.cinema)
                obj.put("moviePoster",movieVO.posterPath)
                obj.put("movieName",movieVO.originalTitle)
                obj.put("duration",movieVO.runtime.toString())
//                Toast.makeText(applicationContext, movieVO.runtime.toString(), Toast.LENGTH_SHORT).show()
            startActivity(ComboSetActivity.getInstance(this,obj.toString(), ticketPrice))
            }
            else
                Toast.makeText(applicationContext, "Please choose your seats!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpRecyclerView() {
        mMovieTicketAdapter = MovieSeatAdapter(this)
        val layoutManager = GridLayoutManager(this, 14)
        rvTicket.layoutManager = layoutManager
        rvTicket.adapter = mMovieTicketAdapter
//        mMovieTicketAdapter.setNewData(DUMMY_SEAT_LIST)

    }

    override fun onTapMovieSeats(seatId: Int, symbol: String) {
        val newSeatList: MutableList<MovieSeatVO> = mutableListOf()
        for (movieSeat in movieSeats) {
            if (movieSeat.type != SEAT_TYPE_TEXT && movieSeat.type != SEAT_TYPE_TOKEN) {
                if (movieSeat.isSelected(seatId, symbol)) {
                    if (movieSeat.isAlreadySelected) {
                        movieSeat.isAlreadySelected = false
                        movieSeat.type = SEAT_TYPE_AVAILABLE
                        removeFromSelectedList(movieSeat)
                    } else {
                        movieSeat.isAlreadySelected = true
                        movieSeat.type = SEAT_TYPE_SELECTED
                        addToSelectedList(movieSeat)

                    }
                }

            }
            newSeatList.add(movieSeat)
        }
        mMovieTicketAdapter.setNewData(newSeatList)
//        Toast.makeText(applicationContext, "$seatId $symbol", Toast.LENGTH_SHORT).show()
    }

    private fun removeFromSelectedList(movieSeat: MovieSeatVO) {
//        for (movieSeatVo in selectedMovieSeats){
//            if (movieSeat==movieSeatVo){
        selectedMovieSeats.remove(movieSeat)
        updateSeatData()
//            }
//        }

    }

    private fun addToSelectedList(movieSeat: MovieSeatVO) {
        selectedMovieSeats.add(movieSeat)
        updateSeatData()
    }

    private fun updateSeatData() {

        tvSeats.text=""
        selectedSeats=""

        tvTicket.text= selectedMovieSeats.count().toString()
        for (movieSeatVO in selectedMovieSeats) {
            selectedSeats += "${movieSeatVO.seatName},"
            tvSeats.text = selectedSeats
            ticketPrice+=movieSeatVO.price
        }
        btnBuyTicket.text="Buy Ticket $ $ticketPrice"
    }

}