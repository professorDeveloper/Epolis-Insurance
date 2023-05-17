package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import kotlinx.android.synthetic.main.succes_otp_screen.*

class SuccessOtpScreen : Fragment(R.layout.succes_otp_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agree_register.setOnClickListener {
            findNavController().navigate(
                R.id.agreeScreen,
                null,
                NavOptions.Builder().setPopUpTo(R.id.successOtpScreen, true).build()
            )
        }
    }
}