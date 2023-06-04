package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.slideUp
import com.azamovhudstc.epolisinsurance.utils.visible
import com.azamovhudstc.epolisinsurance.viewmodel.SuccessOtpScreenViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.SuccessOtpScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.succes_otp_screen.*

@AndroidEntryPoint
class SuccessOtpScreen : Fragment(R.layout.succes_otp_screen) {
    private val viewModel: SuccessOtpScreenViewModel by viewModels<SuccessOtpScreenViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this) {
            if (it) {
                otp_success_progress.visible()
                btn_otp_success.gone()
            } else {
                otp_success_progress.gone()
                btn_otp_success.visible()
            }
        }
        viewModel.clickLiveData.observe(this) {
            findNavController().navigate(
                R.id.agreeScreen,
                null,
                NavOptions.Builder().setPopUpTo(R.id.successOtpScreen, true).build()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_success.visible()
        button_success.slideUp(1000, 0)
        button_success.setOnClickListener {
            viewModel.onClick()
        }
    }
}