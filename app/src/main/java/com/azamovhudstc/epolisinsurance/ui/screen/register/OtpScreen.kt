package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.annotation.SuppressLint
import `in`.aabhasjindal.otptextview.OTPListener
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.data.remote.request.ConfirmRequest
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.visible
import com.azamovhudstc.epolisinsurance.viewmodel.AuthViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AuthViewModelImp
import com.azamovhudstc.sugurtaapp.utils.showSnack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_otp_screen.*
import kotlinx.android.synthetic.main.fragment_otp_screen.view.*
import kotlinx.android.synthetic.main.fragment_register_screen.*
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint
class OtpScreen : Fragment(R.layout.fragment_otp_screen) {
    private val viewModel: AuthViewModel by viewModels<AuthViewModelImp>()
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.confirmResponseLiveData.observe(this){
            AppReference(App.instance).setToken(it.data.token)
            otp_txt.showSuccess()
        }
        viewModel.progressLiveData.observe(this){
            if (it){
                check_code_otp_btn.text=""
                progress_confirm.visible()
            }
            else{
                check_code_otp_btn.text=resources.getString(R.id.send_otp)
                progress_confirm.gone()
            }
        }
        viewModel.errorResponseLiveData.observe(this){
            otp_txt.showError()
            showSnack("Code xato kiritildi")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val  phoneString=arguments?.getString("phoneNumber")
        otp_des.text="${otp_des.text} $phoneString"
        check_code_otp_btn.setOnClickListener {
            if (view.otp_txt.otp!=null){
                viewModel.confirmOtp(ConfirmRequest("",view.otp_txt.otp.toString()))
            }
            else{
                showSnack("Otp Kiriting !")
            }
        }
        otp_txt.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }

            override fun onOTPComplete(otp: String?) {


            }

        }
    }
}