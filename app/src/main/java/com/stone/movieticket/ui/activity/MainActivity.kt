package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.stone.movieticket.R
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import com.stone.movieticket.data.vos.NOW_PLAYING
import com.stone.movieticket.data.vos.ProfileVO
import com.stone.movieticket.data.vos.UserVO
import com.stone.movieticket.delegate.MovieViewHolderDelegate
import com.stone.movieticket.utils.BASE_PROFILE_URL
import com.stone.movieticket.utils.PARAM_COMING_SOON
import com.stone.movieticket.view_pods.MovieListViewPods
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.ivProfile
import kotlinx.android.synthetic.main.activity_main.view.tvName
import kotlinx.android.synthetic.main.layout_drawer.view.*

class MainActivity : AppCompatActivity(), MovieViewHolderDelegate {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var nowShowing: MovieListViewPods
    private lateinit var comingSoon:MovieListViewPods
    private val mMovieTicketModel: MovieTicketModel = MovieTicketModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpNavigationDrawer()
        setUpListener()
        setUpViewPod()
        setUpUserInfo()

        loadData()
    }



    private fun loadData() {
        mMovieTicketModel.getNowPlayingMovie(
            status = NOW_PLAYING,
            onSuccess = {

                nowShowing.setNewData(it)
            },
            onFailure = {
                showMessage(it)
                Log.i("Gooooo",it.toString())
            }
        )
        mMovieTicketModel.getNowPlayingMovie(
            status = PARAM_COMING_SOON,
            onSuccess = {

                comingSoon.setNewData(it)
            },
            onFailure = {
                Log.i("Gooooo",it.toString())
                showMessage(it)
            }
        )
//        mMovieTicketModel.getProfile(
//            onSuccess = {
//                        bindProfileData(it)
//            },
//            onFailure = {
//                showMessage(it)
//            }
//        )
    }

    private fun bindProfileData(profileVO: UserVO) {
        Glide.with(this)
            .load("$BASE_PROFILE_URL${profileVO.profileImage}")
            .into(ivProfile)
        tvName.text="Hi ${profileVO.name}"
        Glide.with(this)
            .load("$BASE_PROFILE_URL${profileVO.profileImage}")
            .into(layoutDrawer.ivProfile)
        layoutDrawer.tvName.text=profileVO.name
        layoutDrawer.tvEmail.text=profileVO.email

    }

    private fun setUpViewPod() {
        nowShowing = vpNowShowing as MovieListViewPods
        nowShowing.setUpMovieListViewPod("Now Showing", this)
        comingSoon = vpComingSoon as MovieListViewPods
        comingSoon.setUpMovieListViewPod("Coming Soon", this)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun setUpUserInfo() {
        mMovieTicketModel.getProfile(
            onSuccess = {
                bindProfileData(it)
            },
            onFailure = {
                showMessage(it)
            }
        )
    }

    private fun setUpListener() {

        ivDrawerMenu.setOnClickListener {
//            if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//                drawerLayout.closeDrawer(GravityCompat.START)
//
//            }else{
            drawerLayout.openDrawer(GravityCompat.START)

//            }
        }
        drawerLayout.tvLogOut.setOnClickListener {
            mMovieTicketModel.logout(
                onSuccess = {
                    showMessage(it)
                    startActivity(LoginActivity.getInstance(this))
                    finish()
                },
                onFailure = {
                    showMessage(it)
                }
            )
        }
    }

    private fun setUpNavigationDrawer() {
        //drawer width
//        val width = Resources.getSystem().displayMetrics.widthPixels
//        val widthOfNav = (width) * 0.82
//        navigation.layoutParams.width = widthOfNav.toInt()
//        navigation.requestLayout()
//        drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this,R.color.black));
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open,
            R.string.close
        )
        toggle.syncState()
    }

    override fun onTapMovie(id: Int) {

        startActivity(MovieDetailActivity.getInstance(this,id))
    }
    private fun showMessage(e: String) {
        Toast.makeText(applicationContext, e, Toast.LENGTH_SHORT).show()
    }
}