package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.View
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.slideUp
import kotlinx.android.synthetic.main.fragment_register_screen.view.*


class RegisterScreen : Fragment(R.layout.fragment_register_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.register_phone.setOnClickListener {
            view.keypad.visibility=View.VISIBLE
            view.keypad.slideUp(1000,0)
            view.register_phone.setRawInputType(InputType.TYPE_NULL)
        }
    }
}