package com.azamovhudstc.epolisinsurance.ui.screen.home.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.data.model.CategoryItem
import com.azamovhudstc.epolisinsurance.tools.hasConnection
import com.azamovhudstc.epolisinsurance.ui.adapter.CategoryAdapter
import com.azamovhudstc.epolisinsurance.ui.adapter.HomeBannerAdapter
import com.azamovhudstc.epolisinsurance.ui.adapter.HomeBottomRecycleAdapter
import com.azamovhudstc.epolisinsurance.utils.LocalData.PERIOD_MS
import com.azamovhudstc.epolisinsurance.utils.LocalData.loadBannerList
import com.azamovhudstc.epolisinsurance.utils.LocalData.loadGridData
import com.azamovhudstc.epolisinsurance.utils.animationTransaction
import com.azamovhudstc.epolisinsurance.utils.convertDpToPixel
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.utils.showNetworkDialog
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class HomeScreen : Fragment(R.layout.fragment_home_screen) {
    lateinit var cateAdapter: CategoryAdapter
    private val data = AppReference(App.instance)
    private fun loadCatHome(): ArrayList<CategoryItem> {
        val arrayList = ArrayList<CategoryItem>()
        when (data.currentLanguage) {
            LanguageType.uz -> {
                arrayList.add(CategoryItem("Hammasi"))
                arrayList.add(CategoryItem("Avtomatik"))
                arrayList.add(CategoryItem("Sayohat"))
            }
            LanguageType.ru -> {
                arrayList.add(CategoryItem("Все"))
                arrayList.add(CategoryItem("Авто"))
                arrayList.add(CategoryItem("Путешествия"))
            }
        }
        return arrayList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (!hasConnection()) {
            showNetworkDialog(requireActivity(),container_home )
        }
        startAutoSlide()
        initIndicator ()
        categoryRv.adapter = cateAdapter
        val list = loadCatHome()
        list.clear()
        list.addAll(loadCatHome())
        cateAdapter.submitList(list)



    }

    private fun startAutoSlide() {
        val adapter = HomeBannerAdapter(loadBannerList())
        cateAdapter = CategoryAdapter()
        categoryRv.adapter = cateAdapter
        cateAdapter.submitList(loadCatHome())
        banner_home_top.adapter = adapter
        val homeBottomRecycleAdapter = HomeBottomRecycleAdapter()
        homeBottomRecycleAdapter.submitList(loadGridData())
        home_bottom_rv.adapter = homeBottomRecycleAdapter
        cateAdapter.setItemClickListener {
        }

        homeBottomRecycleAdapter.setItemClickListener {
            var appReference = AppReference(requireContext());
            if (appReference.token != null) {
                findNavController().navigate(
                    R.id.buyPolisScreen,
                    null,
                    animationTransaction().build()
                )
            } else {
                findNavController().navigate(
                    R.id.registerScreen,
                    null,
                    animationTransaction().build()
                )
            }
        }
        val bannerCount = adapter.itemCount
        var currentPosition = 0

        // Start auto-looping
        viewLifecycleOwner.lifecycleScope.launch {
            while (isActive) {
                delay(PERIOD_MS)
                currentPosition = (currentPosition + 1) % bannerCount
                banner_home_top.setCurrentItem(currentPosition, true)
            }
        }

        // Set a callback for page change events to update the current position
        banner_home_top.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPosition = position
            }
        })


    }


    private fun initIndicator() {
        home_banner_indicator.apply {
            setSliderWidth(convertDpToPixel(15f, requireContext()))
            setSliderHeight(convertDpToPixel(6f, requireContext()))
            setupWithViewPager(banner_home_top)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        banner_home_top.adapter=null

    }



}