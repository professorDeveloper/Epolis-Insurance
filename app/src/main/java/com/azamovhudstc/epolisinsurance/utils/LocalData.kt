package com.azamovhudstc.epolisinsurance.utils

import android.graphics.drawable.ShapeDrawable
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App.Companion.instance
import com.azamovhudstc.epolisinsurance.data.model.ContactUsItem
import com.azamovhudstc.epolisinsurance.data.model.HomeBanner
import com.azamovhudstc.epolisinsurance.data.model.HomeBottomItem
import com.azamovhudstc.epolisinsurance.data.model.PolsItem
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse
import com.azamovhudstc.epolisinsurance.utils.enums.CurrentScreenEnum
import com.azamovhudstc.epolisinsurance.utils.enums.PollsPeopleType
import com.azamovhudstc.epolisinsurance.utils.enums.ScrollType

object LocalData {
     var pollsPeopleType: PollsPeopleType=PollsPeopleType.CustomPolis
    lateinit var setDriverCountListener: ((Int) -> Unit)
    lateinit var removeDisableListener: (Boolean) -> Unit
    lateinit var change: (ScrollType) -> Unit
    lateinit var listenAddProgress:(Boolean) ->Unit
    fun setListenedProgress(listener: (Boolean) -> Unit){
        listenAddProgress=listener
    }

    var removeDisable =true

    fun setDriverCountListener(listener: (Int) -> Unit) {
        setDriverCountListener = listener
    }

    fun setChangeListener(listener: (ScrollType) -> Unit){
        change=listener
    }
    fun setRemoveDisableListeners(listener: (Boolean) -> Unit) {
        removeDisableListener = listener
    }
    lateinit var vehicleResponse: GetVehicleResponse
    lateinit var currentScreenEnumRegisterLogin: CurrentScreenEnum
    var position = 0

    var currentPage = 0
    const val REQUEST_CODE = 0
    var isBuyOrRegistered: Boolean = false
    const val PERIOD_MS: Long = 2000
    fun loadPollList(): ArrayList<PolsItem> {
        val arrayList = ArrayList<PolsItem>()
        arrayList.add(
            PolsItem(
                "Impex Insurance",
                "№ 1WWASD0008",
                "2018-12-27",
                "ОСАГО",
                "2023-12-27",
                "IMPEX"
            )
        )
        arrayList.add(
            PolsItem(
                "Impex Insurance",
                "№ 2DDWS0008",
                "2022-11-29",
                "КАСКО",
                "2023-05-27",
                "KACKO"
            )
        )
        arrayList.add(
            PolsItem(
                "Impex Insurance",
                "№ 2DDEWS008",
                "2023-02-27",
                "ОСАГО",
                "2023-12-27",
                "OCAGO"
            )
        )
        arrayList.add(
            PolsItem(
                "Impex Insurance",
                "№ 2WWGSD0008",
                "2022-11-17",
                "APEX",
                "2023-12-27",
                "APEX"
            )
        )
        return arrayList
    }

    fun loadContactUs(): ArrayList<ContactUsItem> {
        val arrayList = ArrayList<ContactUsItem>()
        arrayList.add(
            ContactUsItem(
                instance.getString(R.string.contact_us1),
                instance.getString(R.string.contact_us_all_description),
                false
            )
        )
        arrayList.add(
            ContactUsItem(
                instance.getString(R.string.contact_us2),
                instance.getString(R.string.contact_us_all_description),
                false
            )
        )
        arrayList.add(
            ContactUsItem(
                instance.getString(R.string.cotact_us3),
                instance.getString(R.string.contact_us_all_description),
                false
            )
        )
        arrayList.add(
            ContactUsItem(
                instance.getString(R.string.contact_us4),
                instance.getString(R.string.contact_us_all_description),
                false
            )
        )
        arrayList.add(
            ContactUsItem(
                instance.getString(R.string.contact_us5),
                instance.getString(R.string.contact_us_all_description),
                false
            )
        )
        return arrayList
    }

    fun addSpinnerCat(): ArrayList<String> {
        var arrayList = ArrayList<String>()
        val arrayOf = arrayOf<ShapeDrawable>()

        arrayList.add("ОСАГО")
        arrayList.add("КАСКО")
        arrayList.add("ОСГО ВТС")
        arrayList.add("ТРЕВЕЛ")
        return arrayList
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

        return arrayList
    }


    fun loadBannerList(): ArrayList<HomeBanner> {
        var bannerList = ArrayList<HomeBanner>()
        bannerList.add(HomeBanner(R.drawable.banner_one))
        bannerList.add(HomeBanner(R.drawable.app_banner))
        bannerList.add(HomeBanner(R.drawable.banner_2))
        return bannerList
    }
}