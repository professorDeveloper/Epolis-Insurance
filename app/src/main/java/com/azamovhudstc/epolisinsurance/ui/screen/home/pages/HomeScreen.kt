package com.azamovhudstc.epolisinsurance.ui.screen.home.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.ui.adapter.CategoryAdapter
import com.azamovhudstc.epolisinsurance.ui.adapter.HomeBannerAdapter
import com.azamovhudstc.epolisinsurance.ui.adapter.HomeBottomRecycleAdapter
import com.azamovhudstc.epolisinsurance.utils.CardTransformer
import com.azamovhudstc.epolisinsurance.utils.HorizontalMarginItemDecoration
import com.azamovhudstc.epolisinsurance.utils.LocalData.PERIOD_MS
import com.azamovhudstc.epolisinsurance.utils.LocalData.currentPage
import com.azamovhudstc.epolisinsurance.utils.LocalData.loadBannerList
import com.azamovhudstc.epolisinsurance.utils.LocalData.loadCatHome
import com.azamovhudstc.epolisinsurance.utils.LocalData.loadGridData
import com.azamovhudstc.sugurtaapp.utils.convertDpToPixel
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class HomeScreen : Fragment(R.layout.fragment_home_screen) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAutoSlide()
        initIndicator()
    }

    private fun startAutoSlide() {
        banner_home_top.currentItem = 2
        banner_home_top.offscreenPageLimit = 1
        banner_home_top.setPageTransformer(CardTransformer(requireContext()))
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        val cateAdapter = CategoryAdapter()
        val adapter = HomeBannerAdapter(loadBannerList())
        categoryRv.adapter = cateAdapter
        cateAdapter.submitList(loadCatHome())
        banner_home_top.adapter = adapter
        val homeBottomRecycleAdapter = HomeBottomRecycleAdapter()
        homeBottomRecycleAdapter.submitList(loadGridData())
        home_bottom_rv.adapter = homeBottomRecycleAdapter
        register_txt.setOnClickListener {
            findNavController().navigate(R.id.registerScreen)
        }
        cateAdapter.setItemClickListener {
        }
        banner_home_top.addItemDecoration(itemDecoration)
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