package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.utils.slideUp
import kotlinx.android.synthetic.main.fragment_register_screen.*
import kotlinx.android.synthetic.main.fragment_register_screen.view.*


class RegisterScreen : Fragment(R.layout.fragment_register_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.send_otp.slideUp(777,0)
    }
}