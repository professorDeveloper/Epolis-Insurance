package com.azamovhudstc.epolisinsurance.utils

import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.model.CategoryItem
import com.azamovhudstc.epolisinsurance.data.model.HomeBanner
import com.azamovhudstc.epolisinsurance.data.model.HomeBottomItem
import kotlin.collections.ArrayList

object LocalData {
    var position =0

    var currentPage = 0
    const val REQUEST_CODE =0
    const val PERIOD_MS: Long = 2000
    fun  loadPollList(){

    }
    fun loadGridData(): ArrayList<HomeBottomItem> {
        val arrayList = ArrayList<HomeBottomItem>()
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
    fun loadCatHome(): ArrayList<CategoryItem> {
        var arrayList = ArrayList<CategoryItem>()
        arrayList.add(CategoryItem(App.instance.resources.getString(R.string.category_item1)))
        arrayList.add(CategoryItem(App.instance.resources.getString(R.string.category_item2)))
        arrayList.add(CategoryItem(App.instance.resources.getString(R.string.category_item3)))
        return arrayList
    }

    fun  loadCat():ArrayList<String>{
        var arrayList=ArrayList<String>()
        arrayList.add(App.instance.resources.getString(R.string.all))
        arrayList.add(App.instance.resources.getString(R.string.working))
        arrayList.add(App.instance.resources.getString(R.string.archive))
        return arrayList
    }
    fun loadBannerList(): ArrayList<HomeBanner> {
        var bannerList = ArrayList<HomeBanner>()
        bannerList.add(HomeBanner(R.drawable.banner_four))
        bannerList.add(HomeBanner(R.drawable.banner_one))
        bannerList.add(HomeBanner(R.drawable.banner_two))
        bannerList.add(HomeBanner(R.drawable.banner_third))
        return bannerList
    }
}