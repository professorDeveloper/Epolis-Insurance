package com.azamovhudstc.epolisinsurance.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.ui.screen.home.pages.HomeScreen
import com.azamovhudstc.epolisinsurance.ui.screen.polis.PolisScreen
import com.azamovhudstc.epolisinsurance.ui.screen.profile.ProfileScreen

class BottomNavAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager,lifecycle) {

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