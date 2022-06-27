package com.stone.movieticket

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.navigation.NavigationView
import com.stone.movieticket.view_pods.MovieListViewPods
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //status bar icon color
        val docker = window.decorView
        val wic = WindowInsetsControllerCompat(window,docker)
        wic.isAppearanceLightStatusBars = true

        setUpNavigationDrawer()
        setUpListener()
        setUpViewPod()


    }

    private fun setUpViewPod() {
        val nowShowing = vpNowShowing as MovieListViewPods
        nowShowing.setUpMovieListViewPod("Now Showing")
        val comingSoon = vpComingSoon as MovieListViewPods
        comingSoon.setUpMovieListViewPod("Coming Soon")
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

    private fun setUpListener() {

        ivDrawerMenu.setOnClickListener {
//            if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//                drawerLayout.closeDrawer(GravityCompat.START)
//
//            }else{
                drawerLayout.openDrawer(GravityCompat.START)

//            }
        }
    }

    private fun setUpNavigationDrawer() {
        //drawer width
//        val width = Resources.getSystem().displayMetrics.widthPixels
//        val widthOfNav = (width) * 0.82
//        navigation.layoutParams.width = widthOfNav.toInt()
//        navigation.requestLayout()
//        drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this,R.color.black));
        val toggle=ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open,
            R.string.close
        )
        toggle.syncState()
    }
}