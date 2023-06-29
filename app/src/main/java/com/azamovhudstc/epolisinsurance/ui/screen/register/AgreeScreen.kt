package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.LocalData.isBuyOrRegistered
import com.azamovhudstc.epolisinsurance.utils.animationTransactionClearStack
import kotlinx.android.synthetic.main.fragment_agree_screen.*

class AgreeScreen : Fragment(R.layout.fragment_agree_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAgree()
    }

    private fun loadAgree() {
        agree_user.setOnCheckedChangeListener { buttonView, isChecked ->
            agree_btn.isEnabled = isChecked
        }
        agree_btn.setOnClickListener {
            if (isBuyOrRegistered) {
                findNavController().navigate(
                    R.id.buyPolisScreen,
                    null, animationTransactionClearStack(R.id.agreeScreen).build()
                )
            } else {
                findNavController().navigate(
                    R.id.mainScreen,
                    null,  animationTransactionClearStack(R.id.agreeScreen).build()
                )

            }
            isBuyOrRegistered = false
        }
    }
}