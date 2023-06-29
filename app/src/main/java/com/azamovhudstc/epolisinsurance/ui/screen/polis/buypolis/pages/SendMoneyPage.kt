package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.utils.showToast
import com.azamovhudstc.epolisinsurance.utils.vibrationAnimation
import kotlinx.android.synthetic.main.fragment_send_money_page.*

class SendMoneyPage : Fragment(R.layout.fragment_send_money_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickContainer.setOnClickListener {
            val checked = click_radioButton.isChecked
            click_radioButton.isChecked=!checked

        }
        next_pay.setOnClickListener {
            if (click_radioButton.isChecked){

            }
            else{
                clickContainer.vibrationAnimation()
                uzumContainer.vibrationAnimation()
                payMeContainer.vibrationAnimation()

            }
        }
        uzumContainer.setOnClickListener {
            uzumContainer.vibrationAnimation()
            showToast(App.instance.getString(R.string.soon))
        }
        payMeContainer.setOnClickListener {
            payMeContainer.vibrationAnimation()
            showToast(App.instance.getString(R.string.soon))

        }

    }

}