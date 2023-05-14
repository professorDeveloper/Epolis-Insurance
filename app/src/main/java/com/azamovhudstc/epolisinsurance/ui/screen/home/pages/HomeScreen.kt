package com.azamovhudstc.epolisinsurance.ui.screen.home.pages

import HomeBannerAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.HomeBottomItem
import com.azamovhudstc.epolisinsurance.ui.adapter.HomeBottomRecycleAdapter
import com.azamovhudstc.epolisinsurance.utils.LocalData.loadBannerList
import com.azamovhudstc.sugurtaapp.utils.convertDpToPixel
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.fragment_main_screen.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class HomeScreen : Fragment(R.layout.fragment_home_screen) {
    private var currentPage = 0
    private val PERIOD_MS: Long = 1500 // Avtomatik kayish oralig'i millisaniyalar
    fun loadGridData(): ArrayList<HomeBottomItem> {
        var arrayList = ArrayList<HomeBottomItem>()
        arrayList.add(
            HomeBottomItem(
                "Обязательное \n" +
                        "Э-ОСАГО", R.drawable.bottom_home_one
            )
        )
        arrayList.add(
            HomeBottomItem(
                "Не обязательное \n" +
                        "Каско",
                R.drawable.home_rv_item_image_default_two
            )
        )
        arrayList.add(
            HomeBottomItem(
                "Туристическая страховка по Узбекистану",
                R.drawable.rv_bottom_home_example
            )
        )
        arrayList.add(
            HomeBottomItem(
                "Туристическая \n страховка по Узбекистану",
                R.drawable.home_rv_item_image_four
            )
        )
        return arrayList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeBannerAdapter(loadBannerList())
        val homeBottomRecycleAdapter = HomeBottomRecycleAdapter()
        homeBottomRecycleAdapter.submitList(loadGridData())
        banner_home_top.adapter = adapter
        home_bottom_rv.adapter=homeBottomRecycleAdapter
        startAutoSlide()
        initIndicator()
    }

    private fun startAutoSlide() {
        lifecycleScope.launch {
            while (isActive) {
                delay(PERIOD_MS)
                banner_home_top.currentItem = currentPage
                currentPage = (currentPage + 1) % banner_home_top.adapter?.itemCount!!
            }
        }
    }


    private fun initIndicator() {
        home_banner_indicator.apply {
            setSliderWidth(convertDpToPixel(15f, requireContext()))
            setSliderHeight(convertDpToPixel(6f, requireContext()))
            setupWithViewPager(banner_home_top)
        }
    }

}