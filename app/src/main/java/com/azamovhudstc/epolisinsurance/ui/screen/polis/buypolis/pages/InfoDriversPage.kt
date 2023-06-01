package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.Driver
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import com.azamovhudstc.epolisinsurance.ui.adapter.DriverAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_info_drivers_page.*
import kotlinx.android.synthetic.main.tab_item_driver.view.*

class InfoDriversPage : Fragment(R.layout.fragment_info_drivers_page) {
    private lateinit var categoryList: ArrayList<TabModel>
    private lateinit var categoryAdapter: DriverAdapter
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryList= ArrayList()
        categoryList.add(TabModel("1", category = Driver("")))
        categoryList.add(TabModel("2", category = Driver("")))
        categoryList.add(TabModel("3", category = Driver("")))
        categoryList.add(TabModel("4", category = Driver("")))
        categoryList.add(TabModel("5", category = Driver("")))
        categoryAdapter = DriverAdapter(requireActivity(),categoryList)
        driverPager.adapter = categoryAdapter
        TabLayoutMediator(driverTab, driverPager) { _, _ ->

        }.attach()
        driverTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tabContainer?.setBackgroundResource(R.drawable.bg_dirver_tab_selected)
                customView?.driver_tab_text?.setTextColor(Color.WHITE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.driver_tab_text?.setTextColor(Color.BLACK)
                customView?.tabContainer?.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
            }



            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })



        setTab()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setTab() {
        val tabCount = driverTab.tabCount
        println("tab count $tabCount")
        println("categoryList ${categoryList.size}")
        for (i in 0 until tabCount) {

                val tabView =
                    LayoutInflater.from(requireActivity()).inflate(R.layout.tab_item_driver, null, false)
                val tab = driverTab.getTabAt(i)

                tab?.customView = tabView
                tabView.driver_tab_text.text = categoryList[i].name
                if (i == 0) {
                    tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_dirver_tab_selected)
                    tabView?.driver_tab_text?.setTextColor(Color.WHITE)
                }
                else {
                    tabView?.driver_tab_text?.setTextColor(Color.BLACK)
                    tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
                }

        }

    }


}