package com.azamovhudstc.epolisinsurance.ui.screen.home.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.data.model.CategoryItem
import com.azamovhudstc.epolisinsurance.ui.adapter.CategoryAdapter
import com.azamovhudstc.epolisinsurance.ui.adapter.HomeBannerAdapter
import com.azamovhudstc.epolisinsurance.ui.adapter.HomeBottomRecycleAdapter
import com.azamovhudstc.epolisinsurance.utils.CardTransformer
import com.azamovhudstc.epolisinsurance.utils.HorizontalMarginItemDecoration
import com.azamovhudstc.epolisinsurance.utils.LocalData.PERIOD_MS
import com.azamovhudstc.epolisinsurance.utils.LocalData.currentPage
import com.azamovhudstc.epolisinsurance.utils.LocalData.loadBannerList
import com.azamovhudstc.epolisinsurance.utils.LocalData.loadGridData
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.sugurtaapp.utils.convertDpToPixel
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

    override fun onResume() {
        super.onResume()
        categoryRv.adapter = cateAdapter
        val list = loadCatHome()
        list.clear()
        list.addAll(loadCatHome())
        cateAdapter.submitList(list)
    }


}