package com.azamovhudstc.epolisinsurance.ui.screen.home.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.ui.adapter.BottomNavAdapter
import kotlinx.android.synthetic.main.fragment_main_screen.*

class MainScreen : Fragment(R.layout.fragment_main_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            val categoryAdapter = BottomNavAdapter(requireActivity())
            mainViewPager.adapter = categoryAdapter
            mainViewPager.isUserInputEnabled = false

            bottom_navigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        mainViewPager.currentItem = 0
                        bottom_navigation.getMenu().getItem(0).setChecked(true);
                    }
                    R.id.polis -> {
                        mainViewPager.currentItem = 1
                        bottom_navigation.getMenu().getItem(1).setChecked(true);
                    }
                    R.id.profile -> {
                        mainViewPager.currentItem = 2
                        bottom_navigation.getMenu().getItem(2).setChecked(true); }
                }

                true
            }

        }
    }

}