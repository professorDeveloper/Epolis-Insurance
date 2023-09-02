package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.slideStart
import com.azamovhudstc.epolisinsurance.utils.slideTop
import kotlinx.android.synthetic.main.fragment_succes_transfer_screen.*


class SuccesTransferScreen : Fragment(R.layout.fragment_succes_transfer_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transferInfoContainer.slideTop(400,0)
        backToHome.slideStart(100,0)
        backToHome.setOnClickListener {
        }
    }
}