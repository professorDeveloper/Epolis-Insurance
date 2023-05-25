package com.azamovhudstc.epolisinsurance.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.ui.screen.home.pages.HomeScreen
import com.azamovhudstc.epolisinsurance.ui.screen.polis.PolisScreen
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.AllInfoPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.InfoContractPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.InfoDriversPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.SendMoneyPage
import com.azamovhudstc.epolisinsurance.ui.screen.profile.ProfileScreen
import com.azamovhudstc.epolisinsurance.ui.screen.welcome.intro_page.PageOne

class BuyPolisAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AllInfoPage()
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