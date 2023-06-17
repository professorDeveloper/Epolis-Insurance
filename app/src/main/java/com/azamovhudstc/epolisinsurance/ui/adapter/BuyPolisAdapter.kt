package com.azamovhudstc.epolisinsurance.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.InfoContractPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.InfoDriversPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.SendMoneyPage

class BuyPolisAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                InfoDriversPage()
            }
            1 -> {
                InfoDriversPage()
            }

            2 -> {
                InfoContractPage()
            }
            else -> {
                SendMoneyPage()
            }
        }

    }
}