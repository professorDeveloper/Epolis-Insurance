package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import com.azamovhudstc.epolisinsurance.ui.adapter.DriverAdapter
import com.azamovhudstc.epolisinsurance.ui.adapter.InfoDriverAdapter
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.item.DriverPageItem
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.LocalData.pollsPeopleType
import com.azamovhudstc.epolisinsurance.utils.LocalData.position
import com.azamovhudstc.epolisinsurance.utils.LocalData.positionDriverItem
import com.azamovhudstc.epolisinsurance.utils.LocalData.removeDisable
import com.azamovhudstc.epolisinsurance.utils.LocalData.setDriverCountListener
import com.azamovhudstc.epolisinsurance.utils.LocalData.setListenerAddNewTab
import com.azamovhudstc.epolisinsurance.utils.LocalData.stepViewController
import com.azamovhudstc.epolisinsurance.utils.enums.DriversType
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.utils.enums.PollsPeopleType
import com.azamovhudstc.epolisinsurance.viewmodel.InfoDriverViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.InfoDriverViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_info_drivers_page.*

@AndroidEntryPoint
class InfoDriversPage : Fragment(R.layout.fragment_info_drivers_page) {
    private lateinit var categoryList: ArrayList<TabModel>
    private val viewModel: InfoDriverViewModel by viewModels<InfoDriverViewModelImp>()
    private lateinit var categoryAdapter: DriverAdapter
    private lateinit var infoDriverAdapter: InfoDriverAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isNextBtnSuccessLiveData.observe(this) {
            if (it == true) {
                stepViewController.isTwoDone = true
                viewpagerChangeListener.invoke(2)
            } else {
                driverPager.vibrationAnimation()
                if (AppReference(requireContext()).currentLanguage == LanguageType.uz) {
                    Toast.makeText(
                        requireContext(),
                        "Minimal bitta xaydovchi qo`shish lozim.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Необходимо добавить хотя бы одного водителя.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        driverTab.slideStart(800, 0)
        next.slideUp(880, 0)
        loadCatData(pollsPeopleType)
        initViewpager()
        next.setOnClickListener {
            viewModel.nextBtn()
        }
        nextBtnInit()
    }

    private fun initViewpager() {

        var count = if (pollsPeopleType == PollsPeopleType.CustomPolis) 1 else 0
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
                val newDriver = TabModel("$count", DriversType.NEW)
                infoDriverAdapter.addItem(newDriver)
                categoryList.add(newDriver)
                val driverPageItem = DriverPageItem()
                categoryAdapter.addItem(newDriver)
                driverPager.currentItem = count
            } else {
                add_Drivers.visible()
                count++
                val newDriver = TabModel("$count", DriversType.NEW)
                categoryList.add(newDriver)
                val driverPageItem = DriverPageItem()
                infoDriverAdapter.addItem(newDriver)
                categoryAdapter.addItem(newDriver)
                driverPager.currentItem = count
            }
        }
        setListenerAddNewTab {
            if (count <= 1) {
                removeDisable = true
            } else {
                removeDisable = false
                driverPager.currentItem = position
            }
            if (count >= 4) {
                count++
                add_Drivers.gone()
                val newDriver = TabModel("$count", DriversType.NEW)
                infoDriverAdapter.addItem(newDriver)
                categoryList.add(newDriver)
                val driverPageItem = DriverPageItem()
                categoryAdapter.addItem(newDriver)
                driverPager.currentItem = count
            } else {
                add_Drivers.visible()
                count++
                val newDriver = TabModel("$count", DriversType.NEW)
                categoryList.add(newDriver)
                val driverPageItem = DriverPageItem()
                infoDriverAdapter.addItem(newDriver)
                categoryAdapter.addItem(newDriver)
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
                positionDriverItem = position

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

    private fun loadCatData(pollsPeopleType: PollsPeopleType) {
        println(pollsPeopleType.name)
        if (pollsPeopleType == PollsPeopleType.CustomPolis) {
            categoryList = ArrayList()
            categoryList.add(TabModel("1", driversDataUI = DriversType.NEW))
        } else {
            categoryList = ArrayList()
        }
    }


    fun nextBtnInit() {
        LocalData.setListenedProgress {
            if (it) {
                add_driver_txt.gone()
                add_driver_progress.visible()
            } else {
                add_driver_progress.gone()
                add_driver_txt.visible()
            }
        }
    }

}

