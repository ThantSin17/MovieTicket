package com.stone.movieticket.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.stone.movieticket.R
import com.stone.movieticket.adapter.LoginPagerAdapter
import com.stone.movieticket.data.model.MovieTicketModel
import com.stone.movieticket.data.model.MovieTicketModelImpl
import com.stone.movieticket.data.vos.ProfileVO
import com.stone.movieticket.delegate.RegisterDelegate
import com.stone.movieticket.ui.fragment.LoginFragment
import com.stone.movieticket.ui.fragment.RegisterFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), RegisterDelegate {


    private val mMovieTicketModel: MovieTicketModel = MovieTicketModelImpl

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val docker = window.decorView
        var wic = WindowInsetsControllerCompat(window, docker)
        wic.isAppearanceLightStatusBars = true

        setUpTabLayout()
        setUpListener()
    }

    override fun onResume() {
        super.onResume()
        setUpLayout(LoginFragment())
    }


    private fun setUpListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.tag) {
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
        login.tag = "login"
        login.text = getString(R.string.lbl_Login)
        tabLayout.addTab(login)
        val register = tabLayout.newTab()
        register.tag = "register"
        register.text = "Register"
        tabLayout.addTab(register)

    }

    private fun setUpLayout(fragment: Fragment) {
        val adapter = LoginPagerAdapter(this)
        vpLogin.adapter = adapter
        TabLayoutMediator(tabLayout, vpLogin) { tab, position ->
            if (position == 0) {
                tab.text = getString(R.string.lbl_Login)
            } else {
                tab.text = getString(R.string.lbl_sign_up)
            }
        }.attach()

    }

    override fun doRegister(name:String,email:String,phone:String, password: String) {
        mMovieTicketModel.register(
            password = password,
            email = email,
            phone = phone,
            name = name,
            onSuccess = {

                Toast.makeText(applicationContext, "Registering Successful", Toast.LENGTH_SHORT).show()
                gotoMainActivity()
//                setUpLayout(LoginFragment())
            },
            onFailure = {
                showError(it)
            },
        )

    }

    override fun doLogin(email: String, password: String) {
        mMovieTicketModel.login(
            email,
            password,
            onSuccess = {

                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                gotoMainActivity()
            },
            onFailure = {
                showError(it)
            },
        )
    }

    private fun gotoMainActivity() {
        startActivity(MainActivity.getInstance(this))
    }

    private fun showError(e: String) {
        Toast.makeText(applicationContext, e, Toast.LENGTH_SHORT).show()
    }

}