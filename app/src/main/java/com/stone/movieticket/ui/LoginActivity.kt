package com.stone.movieticket.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.stone.movieticket.R
import com.stone.movieticket.ui.fragment.LoginFragment
import com.stone.movieticket.ui.fragment.RegisterFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object{
        fun getInstance(context: Context): Intent {
            return  Intent(context,LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val docker = window.decorView
        var wic = WindowInsetsControllerCompat(window,docker)
        wic.isAppearanceLightStatusBars = true

        setUpTabLayout()
        setUpListener()
    }

    override fun onResume() {
        super.onResume()
        setUpLayout(LoginFragment())
    }

    private fun setUpListener() {
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.tag){
                    "login" -> {
                        setUpLayout(LoginFragment())
                    }
                    "register" -> {
                        setUpLayout(RegisterFragment())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setUpTabLayout() {
        val login = tabLayout.newTab()
        login.tag="login"
        login.text="Login"
        tabLayout.addTab(login)
        val register = tabLayout.newTab()
        register.tag="register"
        register.text="Register"
        tabLayout.addTab(register)

    }

    private fun setUpLayout(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frgContainer,fragment)
            .commit()
    }
}