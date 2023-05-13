package com.azamovhudstc.epolisinsurance.ui.screen.home.pages

import HomeBannerAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.model.HomeBanner
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.home_banner_item.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class HomeScreen : Fragment(R.layout.fragment_home_screen) {
    private var currentPage = 0
    private val PERIOD_MS: Long = 1500 // Avtomatik kayish oralig'i millisaniyalar
    fun loadBannerList(): ArrayList<HomeBanner> {
        var bannerList = ArrayList<HomeBanner>()
        bannerList.add(HomeBanner(R.drawable.banner_one))
        bannerList.add(HomeBanner(R.drawable.banner_two))
        bannerList.add(HomeBanner(R.drawable.banner_third))
        return bannerList
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = HomeBannerAdapter(loadBannerList())
        banner_home_top.adapter = adapter
        startAutoSlide()
    }
}