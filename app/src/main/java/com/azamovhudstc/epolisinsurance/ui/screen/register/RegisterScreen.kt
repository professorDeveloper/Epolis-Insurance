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
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.slideUp
import com.azamovhudstc.epolisinsurance.utils.visible
import com.azamovhudstc.epolisinsurance.viewmodel.AuthViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AuthViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register_screen.*
import kotlinx.android.synthetic.main.fragment_register_screen.view.*


@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.fragment_register_screen) {
    private val viewModel: AuthViewModel by viewModels<AuthViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.registerResponseLiveData.observe(this){
            val bundle=Bundle()
            bundle.putString(register_phone.unMaskedText,"phoneNumber")
            findNavController().navigate(R.id.otpScreen,bundle)
        }
        viewModel.progressLiveData.observe(this){
            if (it){
                send_otp.text=""
                progress_Register.visible()
            }
            else{
                send_otp.text=resources.getString(R.string.send_otp)
                progress_Register.gone()
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
        send_otp.setOnClickListener {
            viewModel.registerUser(RegisterRequest("",register_phone.unMaskedText.toString()))
        }
    }
}