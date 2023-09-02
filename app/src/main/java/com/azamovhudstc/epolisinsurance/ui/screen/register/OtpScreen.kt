package com.azamovhudstc.epolisinsurance.ui.screen.register

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.data.remote.request.ConfirmRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.viewmodel.AuthViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AuthViewModelImp
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_otp_screen.*
import kotlinx.android.synthetic.main.fragment_otp_screen.view.*
import kotlinx.coroutines.delay


@AndroidEntryPoint
class OtpScreen : Fragment(R.layout.fragment_otp_screen) {
    private val viewModel: AuthViewModel by viewModels<AuthViewModelImp>()
    private lateinit var smsVerifyCatcher: SmsVerifyCatcher
    private var isGranted: Boolean = false
    private val countDownLiveData = MutableLiveData<Int>()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.confirmResponseLiveData.observe(this) {
            val shared = AppReference(App.instance)
            shared.token = it?.data!!.token!!
            val phoneString = arguments?.getString("phone")
            shared.phone = "+" + phoneString.toString()
            otp_txt.showSuccess()
            findNavController().navigate(
                R.id.successOtpScreen,
                null,
                animationTransactionClearStack(R.id.otpScreen).build()
            )
        }
        viewModel.progressLiveData.observe(this) {
            if (it) {
                visible_otp_progress.visible()
                btn_Register_Txt.gone()
            } else {
                visible_otp_progress.gone()
                btn_Register_Txt.visible()
            }
        }
        viewModel.errorResponseLiveData.observe(this) {
            otp_txt.showError()
            showToast(getString(R.string.error_code))
        }
        viewModel.registerResponseLiveData.observe(this) {
            showToastSuccess(getString(R.string.code_resend))
            check_code_otp_btn.isEnabled = true
            initTimer()
            otp_txt.setOTP("")
        }
        countDownLiveData.observe(this, countDownObserver)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phoneString = arguments?.getString("phone")
        println(phoneString)
        don_t_have_code_container.setOnClickListener {
            viewModel.registerUser(RegisterRequest("", phoneString.toString()))
        }
        otp_des.text = "${otp_des.text} $phoneString"
        check_code_otp_btn.setOnClickListener {
            if (view.otp_txt.otp.length == 6) {
                viewModel.confirmOtp(ConfirmRequest("$phoneString", view.otp_txt.otp.toString()))
            } else {
                showToast(getString(R.string.sms_code_error))
            }
        }
        otp_txt.otpListener = object : OTPListener {
            override fun onInteractionListener() {
            }

            override fun onOTPComplete(otp: String?) {
                viewModel.confirmOtp(ConfirmRequest("$phoneString", view.otp_txt.otp.toString()))
            }

        }
        back_otp.setOnClickListener {
            println("Qayt")
            findNavController().popBackStack()
        }
        initSmsVerifyCatcher()
        initTimer()
    }

    private fun initSmsVerifyCatcher() {
        isGranted = arguments?.getBoolean("isPermission")!!
        if (isGranted)
            smsVerifyCatcher = SmsVerifyCatcher(
                activity
            ) { message ->
                otp_txt.setOTP(message.parseCode())
            }
        else {
            println("isnotGranted")
        }
    }

    private fun initTimer() {
        var count = 60

        lifecycleScope.launchWhenResumed {
            while (count > 0) {
                delay(1000)
                count -= 1
                countDownLiveData.value = count
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (isGranted)
            smsVerifyCatcher.onStart()
        else {
            println("isnotGranted")
        }
    }

    override fun onStop() {
        super.onStop()
        if (isGranted)
            smsVerifyCatcher.onStop()
        else println("isnotGranted")
    }

    @SuppressLint("SetTextI18n")
    private val countDownObserver = Observer<Int> {
        if (it != 0) {
            resend_time_container.visible()
            don_t_have_code_container.gone()
            resend_time.text = "00:$it"
        } else {
            resend_time_container.visibility = View.GONE
            check_code_otp_btn.isEnabled = false

            don_t_have_code_container.visibility = View.VISIBLE
        }
    }


}