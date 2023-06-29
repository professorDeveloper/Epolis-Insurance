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
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.item.DriverPageItem
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.LocalData.position
import com.azamovhudstc.epolisinsurance.utils.LocalData.removeDisable
import com.azamovhudstc.epolisinsurance.utils.LocalData.setDriverCountListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_info_drivers_page.*

@AndroidEntryPoint
class InfoDriversPage : Fragment(R.layout.fragment_info_drivers_page) {
    private lateinit var categoryList: ArrayList<TabModel>
    private lateinit var categoryAdapter: DriverAdapter
    private lateinit var infoDriverAdapter: InfoDriverAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCatData()
        initViewpager()
        next.setOnClickListener {
            viewpagerChangeListener.invoke(2)
        }
        nextBtnInit()
    }

    private fun initViewpager() {
        var count = 1
        infoDriverAdapter = InfoDriverAdapter()
        categoryAdapter = DriverAdapter(parentFragmentManager, lifecycle)
        infoDriverAdapter.submitList(categoryList)
        categoryAdapter.submitList(categoryList)
        driverTab.adapter = infoDriverAdapter
        driverPager.adapter = categoryAdapter
        driverPager.isUserInputEnabled = false
        add_Drivers.setSafeOnClickListener {
            if (count <= 1) {
                removeDisable = true
            } else {
                removeDisable = false
                driverPager.currentItem = position
            }
            if (count >= 4) {
                count++
                add_Drivers.gone()
                val newDriver = TabModel("$count")
                infoDriverAdapter.addItem(newDriver)
                categoryList.add(newDriver)
                val driverPageItem = DriverPageItem()
                categoryAdapter.addItem(newDriver, driverPageItem)
                driverPager.currentItem = count
            } else {
                add_Drivers.visible()
                count++
                val newDriver = TabModel("$count")
                categoryList.add(newDriver)
                val driverPageItem = DriverPageItem()
                infoDriverAdapter.addItem(newDriver)
                categoryAdapter.addItem(newDriver, driverPageItem)
                driverPager.currentItem = count
            }
        }
        infoDriverAdapter.setDeleteItemClickListener { tabModel, position ->
            if (count <= 1) {
                removeDisable = true
            } else {
                removeDisable = false
                driverPager.currentItem = position
            }

        }
        categoryAdapter.setRemoveViewPager { tabModel, position ->
            removeDisable = count <= 2
            count--
            add_Drivers.visible()
            categoryList.removeAt(position)
            categoryAdapter.removeItem(position)
            infoDriverAdapter.removeItem(position)

        }

        driverPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setDriverCountListener.invoke(position)
                if (count <= 1) {
                    removeDisable = true
                } else {
                    removeDisable = false
                    driverPager.currentItem = position
                }
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

    fun nextBtnInit(){
        LocalData.setListenedProgress {
            if (it){
                add_driver_txt.gone()
                add_driver_progress.visible()
            }
            else{
                add_driver_progress.gone()
                add_driver_txt.visible()
            }
        }
    }

}

