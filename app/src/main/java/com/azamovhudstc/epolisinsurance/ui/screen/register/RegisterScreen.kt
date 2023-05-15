package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.slideUp
import kotlinx.android.synthetic.main.fragment_register_screen.*
import kotlinx.android.synthetic.main.fragment_register_screen.view.*


class RegisterScreen : Fragment(R.layout.fragment_register_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.send_otp.slideUp(777, 0)
        view.register_phone.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                return true
            }
        })
        send_otp.setOnClickListener {
            findNavController().navigate(R.id.otpScreen)
        }
    }
}