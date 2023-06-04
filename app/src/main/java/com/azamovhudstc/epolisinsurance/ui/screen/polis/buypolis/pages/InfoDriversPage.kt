package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import com.azamovhudstc.epolisinsurance.ui.adapter.DriverAdapter
import com.azamovhudstc.epolisinsurance.ui.adapter.InfoDriverAdapter
import com.azamovhudstc.epolisinsurance.utils.LocalData.setDriverCountListener
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.visible
import kotlinx.android.synthetic.main.fragment_info_drivers_page.*
import kotlinx.android.synthetic.main.tab_item_driver.view.*

class InfoDriversPage : Fragment(R.layout.fragment_info_drivers_page) {
    private lateinit var categoryList: ArrayList<TabModel>
    private lateinit var categoryAdapter: DriverAdapter
    private lateinit var infoDriverAdapter: InfoDriverAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCatData()
        var count = 1
        infoDriverAdapter = InfoDriverAdapter()
        categoryAdapter = DriverAdapter()
        infoDriverAdapter.submitList(categoryList)
        categoryAdapter.submitList(categoryList)
        driverTab.adapter = infoDriverAdapter
        driverPager.isUserInputEnabled=false
        driverPager.adapter = categoryAdapter
        add_Drivers.setOnClickListener {
            if (count >= 4) {
                count++
                add_Drivers.gone()
                val newDriver = TabModel("$count")
                infoDriverAdapter.addItem(newDriver)
                categoryList.add(newDriver)
                categoryAdapter.addItem(newDriver)
                driverPager.currentItem=count
            } else {
                add_Drivers.visible()
                count++
                val newDriver = TabModel("$count")
                categoryList.add(newDriver)
                infoDriverAdapter.addItem(newDriver)
                categoryAdapter.addItem(newDriver)
                driverPager.currentItem=count
            }
        }
        infoDriverAdapter.setDeleteItemClickListener { tabModel, position ->
            driverPager.currentItem = position

        }
        categoryAdapter.setRemoveViewPager { tabModel, position ->
            add_Drivers.visible()
            count--
            categoryList.removeAt(position)
            categoryAdapter.removeItem(position)
            infoDriverAdapter.removeItem(position)
        }


        driverPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setDriverCountListener.invoke(position)
            }
        })

    }


    private fun loadCatData() {
        categoryList = ArrayList()
        categoryList.add(TabModel("1"))
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun setTab() {
//        val tabCount = driverTab.tabCount
//        println("tab count $tabCount")
//        println("categoryList ${categoryList.size}")
//        for (i in 0 until tabCount) {
//            val tabView =
//                LayoutInflater.from(requireActivity())
//                    .inflate(R.layout.tab_item_driver, null, false)
//            val tab = driverTab.getTabAt(i)
//            tab?.customView = tabView
//            tabView.driver_tab_text.text = categoryList[i].name
//            if (driverTab.getTabAt(i)?.isSelected == true) {
//                tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_dirver_tab_selected)
//                tabView?.driver_tab_text?.setTextColor(Color.WHITE)
//            } else {
//                tabView?.driver_tab_text?.setTextColor(Color.BLACK)
//                tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
//            }
//
//        }
//
//    }


}


/**
 * if (count >= 4) {
count++
add_Drivers.gone()
val newDriver = TabModel("$count")
println("asdasdasda")
val newTab = driverTab.newTab()
val tabView =
LayoutInflater.from(requireActivity())
.inflate(R.layout.tab_item_driver, null, false)

newTab.customView = tabView
tabView?.driver_tab_text?.setTextColor(Color.BLACK)
tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
driverTab.addTab(newTab, false)
categoryList.add(newDriver)
categoryAdapter.addTab(newDriver)
for (i in 0 until driverTab.tabCount) {
val tabView =
LayoutInflater.from(requireActivity())
.inflate(R.layout.tab_item_driver, null, false)

val tab = driverTab.getTabAt(i)
tab?.customView = tabView
tabView.driver_tab_text.text = categoryList[i].name
if (driverTab.getTabAt(i)?.isSelected == true) {
tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_dirver_tab_selected)
tabView?.driver_tab_text?.setTextColor(Color.WHITE)
} else {
tabView?.driver_tab_text?.setTextColor(Color.BLACK)
tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
}

}
} else {
add_Drivers.visible()
count++
var newDriver = TabModel("$count")
println("asdasdasda")
val newTab = driverTab.newTab()
val tabView =
LayoutInflater.from(requireActivity())
.inflate(R.layout.tab_item_driver, null, false)

newTab.customView = tabView
tabView?.driver_tab_text?.setTextColor(Color.BLACK)
tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
driverTab.addTab(newTab, false)
categoryList.add(newDriver)
categoryAdapter.addTab(newDriver)
for (i in 0 until driverTab.tabCount) {
val tabView =
LayoutInflater.from(requireActivity())
.inflate(R.layout.tab_item_driver, null, false)

val tab = driverTab.getTabAt(i)
tab?.customView = tabView
tabView.driver_tab_text.text = categoryList[i].name
if (driverTab.getTabAt(i)?.isSelected == true) {
tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_dirver_tab_selected)
tabView?.driver_tab_text?.setTextColor(Color.WHITE)
} else {
tabView?.driver_tab_text?.setTextColor(Color.BLACK)
tabView?.tabContainer?.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
}

}
}
 * **/

/*
          val deletedTab = driverTab.getTabAt(it - 1)
            driverTab.removeTab(deletedTab!!)
            count--
            add_Drivers.visible()
            categoryAdapter.removeTab(categoryList.get(it-1))
            categoryList.removeAt(it-1)
            categoryList.onEach {data ->
                println(data.name)
                if (it<=data.name.toInt())
                {
                    data.name=(data.name.toInt()-1).toString()
                }
            }
            categoryList.onEach {
                println("changed :${it.name}")
            }
            setTab()
        }


        categoryAdapter.loadData(categoryList)
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

/
 */

fun main() {

}