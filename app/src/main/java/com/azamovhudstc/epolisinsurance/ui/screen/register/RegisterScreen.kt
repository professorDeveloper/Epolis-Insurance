package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.viewmodel.AuthViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AuthViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register_screen.*
import kotlinx.android.synthetic.main.fragment_register_screen.view.*


@AndroidEntryPoint
class RegisterScreen :
    Fragment(com.azamovhudstc.epolisinsurance.R.layout.fragment_register_screen) {
    private val viewModel: AuthViewModel by viewModels<AuthViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorResponseLiveData.observe(this) {
            showToast(it.toString())
        }
        viewModel.registerResponseLiveData.observe(this) {
            val bundle = Bundle()
            bundle.putString("phone", "998${register_phone.unMaskedText.toString()}")
            findNavController().navigate(R.id.otpScreen, bundle)
        }
        viewModel.progressLiveData.observe(this) {
            if (it) {
                btn_Register_Txt.gone()
                visible_registeR_progress.visible()
            } else {
                visible_registeR_progress.gone()
                btn_Register_Txt.visible()

            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.send_otp.slideUp(777, 0)
        view.register_phone.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                return true
            }
        })
        back_btn.setOnClickListener {
            findNavController().popBackStack()
        }
        send_otp.setOnClickListener {
            if ("998${register_phone.unMaskedText}".checkPhone()) {
                viewModel.registerUser(
                    RegisterRequest(
                        "",
                        "998${register_phone.unMaskedText.toString()}"
                    )
                )
            } else {
                showToast(getString(R.string.error_phone))
            }
        }
    }


}