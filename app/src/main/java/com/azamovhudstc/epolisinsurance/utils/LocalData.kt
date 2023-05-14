package com.azamovhudstc.epolisinsurance.utils

import android.os.Build
import android.preference.PreferenceManager
import android.annotation.TargetApi
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.CategoryItem
import com.azamovhudstc.epolisinsurance.data.model.HomeBanner
import java.util.*
import kotlin.collections.ArrayList

object LocalData {
    var position =0

    fun loadBannerList(): ArrayList<HomeBanner> {
        var bannerList = ArrayList<HomeBanner>()
        bannerList.add(HomeBanner(R.drawable.banner_four))
        bannerList.add(HomeBanner(R.drawable.banner_one))
        bannerList.add(HomeBanner(R.drawable.banner_two))
        bannerList.add(HomeBanner(R.drawable.banner_third))
        return bannerList
    }
}