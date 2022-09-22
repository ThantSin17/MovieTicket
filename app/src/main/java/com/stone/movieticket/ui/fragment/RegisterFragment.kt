package com.stone.movieticket.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.stone.movieticket.R
import com.stone.movieticket.data.vos.ProfileVO
import com.stone.movieticket.delegate.LoginDelegate
import com.stone.movieticket.delegate.RegisterDelegate
import com.stone.movieticket.view_pods.LoginButtonViewPods
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(),LoginDelegate {

    private lateinit var loginViewPod:LoginButtonViewPods
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPod()
        setUpListener()
    }

    private fun setUpListener() {
//        vpRegister.btnRegister.text = getText(R.string.lbl_sign_up)
//        vpRegister.btnRegister.setOnClickListener {
//            Toast.makeText(context, "Register", Toast.LENGTH_SHORT).show()
//        }
//        loginViewPod
        editEmail.addTextChangedListener(object :TextWatcher{
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
        editPassword.addTextChangedListener(object :TextWatcher{
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

    override fun onResume() {
        super.onResume()
        rlRegister.requestLayout()
    }

    private fun setUpViewPod(){

        loginViewPod = vpRegister as LoginButtonViewPods
        loginViewPod.setUpButtonViewPod(getText(R.string.lbl_sign_up))
        loginViewPod.onClickRegisterButton(this)
    }

    override fun onTapConfirm() {
        var isHasError = true

            if (editEmail.text?.isEmpty() == true) {
                editEmail.error = "Email can't be Empty"
                if (editEmail.error != null) {
                    isHasError = false
                }
            }


            if (editPassword.text?.isEmpty() == true) {
                editPassword.error = "Password can't be Empty"
                if (editPassword.error != null) {
                    isHasError = false
                }

            }


            if (editName.text?.isEmpty() == true) {
                editName.error = "Name can't be Empty"
                if (editName.error != null) {
                    isHasError = false
                }
            }



            if (editPhone.text?.isEmpty() == true) {
                editPhone.error = "Phone number can't be Empty"
                if (editPhone.error != null) {
                    isHasError = false
                }
            }
        if (isHasError){
            doRegister()
        }

    }
    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun doRegister(){
//        val profileVO=ProfileVO(
//            id=0,
//            name = editName.text.toString(),
//            email = editEmail.text.toString(),
//            phone = editPhone.text.toString(),
//            totalExpense = 0,
//            profileImage = ""
//        )

        registerDelegate.doRegister(name = editName.text.toString(), email = editEmail.text.toString(),editPhone.text.toString(), password = editPassword.text.toString())
    }


}