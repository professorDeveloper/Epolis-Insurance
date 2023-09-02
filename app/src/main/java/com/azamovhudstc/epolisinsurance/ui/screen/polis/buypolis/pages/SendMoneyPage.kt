package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.LocalData.getPrice
import com.azamovhudstc.epolisinsurance.utils.LocalData.getPriceInt
import com.azamovhudstc.epolisinsurance.utils.vibrationAnimation
import kotlinx.android.synthetic.main.fragment_send_money_page.*

class SendMoneyPage : Fragment(R.layout.fragment_send_money_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }
    fun initView(){

        pricePolis.text=getPrice()
        next_pay.setOnClickListener {
           initClick()

        }
        uzumContainer.setOnClickListener {
            Toast.makeText(requireContext(), R.string.soon, Toast.LENGTH_SHORT).show()
            uzumContainer.vibrationAnimation()
        }
        payMeContainer.setOnClickListener {
            payMeContainer.vibrationAnimation()
            Toast.makeText(requireContext(), R.string.soon, Toast.LENGTH_SHORT).show()
        }
    }
    private fun initClick(){
        val url = "https://my.click.uz/services/pay/?service_id=${26078}&merchant_id=${14484}&amount=${getPriceInt()}&transaction_param=${29644}"
        println(url)
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)

//        val config = ClickMerchantConfig.Builder()
//            .serviceId(26078)
//            .merchantId(14484)
//
//            .amount(1500.0)
//            .transactionParam(transactionParam)
//            .locale("UZ")
//            .option(PaymentOptionEnum.USSD)
//            .theme(ThemeOptions.LIGHT)
//
//            .productName(productName)
//            .productDescription(productDescription)
//            .merchantUserId(29644)
//            .requestId(currentUser.requestId)
//
//            .build()
//
//        ClickMerchantManager.logs = true
//        ClickMerchant.init(
//            requireFragmentManager(), config,
//            object : ClickMerchantListener {
//                override fun onReceiveRequestId(id: String) {
//                    currentUser.requestId = id
//                }
//
//                override fun onSuccess(paymentId: Long) {
//                    currentUser.paymentId = paymentId
//                    currentUser.paid = true
//                }
//
//                override fun onFailure() {
//                    currentUser.requestId = ""
//                }
//
//                override fun onInvoiceCancelled() {
//                    currentUser.requestId = ""
//                }
//
//                override fun closeDialog() {
//                    ClickMerchant.dismiss()
//                }
//            }
//        )


    }


}