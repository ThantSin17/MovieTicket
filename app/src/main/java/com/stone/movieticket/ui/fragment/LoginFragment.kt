package com.stone.movieticket.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stone.movieticket.R
import com.stone.movieticket.delegate.LoginDelegate
import com.stone.movieticket.delegate.RegisterDelegate
import com.stone.movieticket.view_pods.LoginButtonViewPods
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.editEmail
import kotlinx.android.synthetic.main.fragment_login.editPassword
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.view_pod_login_btn.view.*

class LoginFragment : Fragment() ,LoginDelegate{

    private lateinit var loginViewPod: LoginButtonViewPods
    private lateinit var registerDelegate: RegisterDelegate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerDelegate =  context as RegisterDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPod()
        setUpListener()
    }

    private fun setUpListener() {
        editEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isEmailValid(s)){
                    editEmail.error="enter valid email"
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        editPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((editPassword.text?.length ?: 0) < 6){
                    editPassword.error="The password must be at least 6 characters."
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    private fun setUpViewPod(){

        loginViewPod = vpLogin as LoginButtonViewPods
        loginViewPod.setUpButtonViewPod(getText(R.string.lbl_confirm))
        loginViewPod.onClickRegisterButton(this)
    }

    override fun onResume() {
        super.onResume()
        rlLogin.requestLayout()
    }

    override fun onTapConfirm() {
        var isHasNoError=true
        if (editEmail.text?.isEmpty() == true) {
            editEmail.error = "Email can't be Empty"
            if (editEmail.error != null) {
                isHasNoError = false
            }
        }


        if (editPassword.text?.isEmpty() == true) {
            editPassword.error = "Password can't be Empty"
            if (editPassword.error != null) {
                isHasNoError = false
            }

        }
        if (isHasNoError){
            registerDelegate.doLogin(email =editEmail.text.toString(), password = editPassword.text.toString())
        }
    }
    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}