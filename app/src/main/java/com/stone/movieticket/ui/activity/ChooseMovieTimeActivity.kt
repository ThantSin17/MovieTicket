package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stone.movieticket.R
import com.stone.movieticket.adapter.DayListAdapter
import com.stone.movieticket.adapter.MovieTimeAdapter
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import com.stone.movieticket.data.vos.CinemaVO
import com.stone.movieticket.data.vos.DateVO
import com.stone.movieticket.data.vos.MovieVO
import com.stone.movieticket.data.vos.TimeslotVO
import com.stone.movieticket.delegate.ChooseDateDelegate
import com.stone.movieticket.delegate.TimeSlotDelegate
import kotlinx.android.synthetic.main.activity_choose_movie_time.*
import java.text.SimpleDateFormat
import java.util.*


class ChooseMovieTimeActivity : AppCompatActivity(), ChooseDateDelegate, TimeSlotDelegate {
    private lateinit var mDayListAdapter: DayListAdapter
    private lateinit var selectedDate: DateVO
    private lateinit var selectedTimeSlot: TimeslotVO
    private lateinit var selectedCinema: CinemaVO

    private lateinit var mMovieTimeAdapter: MovieTimeAdapter
    private val dateList: MutableList<DateVO> = mutableListOf()
    private val mMovieTicketModel: MovieTicketModel = MovieTicketModelImpl
    private val cinemaList: MutableList<CinemaVO> = mutableListOf()
    private lateinit var movieVO: MovieVO
    private var movieId: Int? = 0

    companion object {
        private const val EXTRA_MOVIE_VO = "EXTRA_MOVIE_ID"
        fun getInstance(context: Context, movieVO: MovieVO): Intent {
            val intent = Intent(context, ChooseMovieTimeActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_VO, movieVO)
            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_movie_time)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }


        //status bar icon color
//        val docker = window.decorView
//        val wic = WindowInsetsControllerCompat(window,docker)
//        wic.isAppearanceLightStatusBars = true

        setUpRecyclerView()
        setUpViewPod()
        setUpListener()
        setUpDates()
        movieVO = intent?.getSerializableExtra(EXTRA_MOVIE_VO) as MovieVO


        requestData()
    }

    private fun requestData() {
        mMovieTicketModel.getCinemaList(
            selectedDate = selectedDate.time,
            movieId = movieId.toString(),
            onSuccess = {
                bindCinemaData(it)
            },
            onFailure = {
                showMessage(it)
            }
        )
    }

    private fun bindCinemaData(cinemas: List<CinemaVO>) {
        mMovieTimeAdapter.setNewData(cinemas)
        cinemaList.clear()
        cinemaList.addAll(cinemas)
    }


    private fun setUpDates() {

        val calendar = Calendar.getInstance()

        for (i in 0..13) {

            val fTime = SimpleDateFormat("yyyy-MM-dd")
            val fDay = SimpleDateFormat("EEE")
            val fDate = SimpleDateFormat("dd")
            val dateVO = DateVO(
                id = i,
                date = fDate.format(calendar.time),
                day = fDay.format(calendar.time),
                time = fTime.format(calendar.time),
                isSelected = false
            )
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            dateList.add(dateVO)
        }
        dateList[0].isSelected = true
        selectedDate = dateList[0]
        mDayListAdapter.setNewData(dateList)

    }

    private fun setUpListener() {

        btnNext.setOnClickListener {
            if (this::selectedTimeSlot.isInitialized && selectedTimeSlot.isSelected)
                startActivity(
                    ChooseMovieSeatActivity.getInstance(
                        this,
                        dateVO = selectedDate,
                        timeslotVO = selectedTimeSlot,
                        movieVO = movieVO,
                        cinemaVO = selectedCinema
                    )
                )
            else showMessage("choose time slot first")

        }
        ivBack.setOnClickListener { onBackPressed() }
    }

    private fun setUpViewPod() {
    }

    private fun setUpRecyclerView() {
        mDayListAdapter = DayListAdapter(this)
        rvDay.adapter = mDayListAdapter
        mMovieTimeAdapter = MovieTimeAdapter(this)
        rvMovieTime.adapter = mMovieTimeAdapter
    }

    override fun chooseDate(id: Int) {
        val newDateList: MutableList<DateVO> = mutableListOf()
        for (dateVO in dateList) {
            dateVO.isSelected = dateVO.id == id
            if (dateVO.isSelected) selectedDate = dateVO
            newDateList.add(dateVO)
        }
        mDayListAdapter.setNewData(newDateList)
        requestData()
    }

    private fun showMessage(e: String) {
        Toast.makeText(applicationContext, e, Toast.LENGTH_SHORT).show()
    }

    override fun onTapTimeSlot(timeSlotId: Int) {
        val newCinemaList: MutableList<CinemaVO> = mutableListOf()

        for (cinema in cinemaList) {

            val newTimeSlotList: MutableList<TimeslotVO> = mutableListOf()
            for (timeslot in cinema.timeslots) {
                timeslot.isSelected = timeslot.cinemaDayTimeslotId == timeSlotId
                if (timeslot.isSelected) {
                    selectedTimeSlot = timeslot
                    selectedCinema = cinema
                }
                newTimeSlotList.add(timeslot)
            }
            cinema.timeslots = newTimeSlotList
            newCinemaList.add(cinema)
        }
        mMovieTimeAdapter.setNewData(newCinemaList)

    }
}