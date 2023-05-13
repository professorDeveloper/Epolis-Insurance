package com.azamovhudstc.epolisinsurance.ui.adapter

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.ui.screen.home.pages.HomeScreen
import com.azamovhudstc.epolisinsurance.ui.screen.home.pages.PolisScreen
import com.azamovhudstc.epolisinsurance.ui.screen.home.pages.ProfileScreen

class BottomNavAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeScreen()
            }
            1 -> {
                PolisScreen()
            }

            else -> {
                ProfileScreen()
            }
        }

    }
}