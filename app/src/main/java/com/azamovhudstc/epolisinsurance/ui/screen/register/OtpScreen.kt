package com.azamovhudstc.epolisinsurance.ui.screen.register

import `in`.aabhasjindal.otptextview.OTPListener
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import kotlinx.android.synthetic.main.fragment_otp_screen.*


class OtpScreen : Fragment(R.layout.fragment_otp_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        check_code_otp_btn.setOnClickListener {
            findNavController().navigate(R.id.successOtpScreen)
        }
        otp_txt.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }
            override fun onOTPComplete(otp: String?) {
                if (otp.toString().equals("123456")) {
                    otp_txt.showSuccess()
                } else {
                    otp_txt.showError()
                }
            }

        }
    }
}