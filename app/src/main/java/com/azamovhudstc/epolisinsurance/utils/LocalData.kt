package com.azamovhudstc.epolisinsurance.utils

import android.graphics.drawable.ShapeDrawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.app.App.Companion.instance
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.data.model.ContactUsItem
import com.azamovhudstc.epolisinsurance.data.model.HomeBanner
import com.azamovhudstc.epolisinsurance.data.model.HomeBottomItem
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRemoveRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitPolicyRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.Driver
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverLocal
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse
import com.azamovhudstc.epolisinsurance.data.ui.DriversDataUI
import com.azamovhudstc.epolisinsurance.data.ui.StepViewDataUI
import com.azamovhudstc.epolisinsurance.utils.enums.CurrentScreenEnum
import com.azamovhudstc.epolisinsurance.utils.enums.DriversType
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.utils.enums.PollsPeopleType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalData {
    var  submitPolicyRequest:SubmitPolicyRequest?=null
    val stepViewController = StepViewDataUI(
        isOneDone = false,
        isTwoDone = false,
        isThirdDone = false,
        isFourDone = false
    )
    var driversController = DriversDataUI(
        oneDriver = DriversType.NEW,
        twoDriver = DriversType.NEW,
        thirdDriver = DriversType.NEW,
        fourDriver = DriversType.NEW,
        fiveDriver = DriversType.NEW,
    )

    fun isDonePosition(position: Int): Boolean {
        return when (position) {
            1 -> stepViewController.isOneDone
            2 -> stepViewController.isTwoDone
            3 -> stepViewController.isThirdDone
            else -> stepViewController.isFourDone
        }
    }

    var pollsPeopleType: PollsPeopleType = PollsPeopleType.CustomPolis
    lateinit var setDriverCountListener: ((Int) -> Unit)
    var vehicleId: Int = 297
    var govNumber = "01h548ra"
    var driverList = ArrayList<DriverLocal>()
    fun getPrice(): String {
        val carRegionType = govNumber.substring(0, 2).toInt()
        return if (pollsPeopleType == PollsPeopleType.PremiumPolls) {
            if (carRegionType in 10 downTo 1) {
                instance.getString(R.string.tashkent_Region_premium_polis_price)
            } else {
                instance.getString(R.string.regions_premium_polis_price_with_out_tashkent)
            }
        } else {
            if (carRegionType in 10 downTo 1) {
                instance.getString(R.string.tashkent_Region_no_premium_polis_price)
            } else {
                instance.getString(R.string.regions_no_premium_polis_price_with_out_tashkent)
            }

        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun String.reformatDate() :String{
        // Berilgan san'ani "dd-MM-yyyy" formatida LocalDate obyektiga o'tkazamiz
        val inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(this, inputFormatter)

        // Yangi formatda qaytarish
         return date.format(outputFormatter)
    }

    fun getPriceInt(): Int {
        return if (AppReference(App.instance).currentLanguage == LanguageType.ru) {
            var num = getPrice().replace("сум", "")
            var num1 = num.replace(" ", "")

            Integer.parseInt(num1)

        } else {
            var num = getPrice().replace("so`m", "")
            var num1 = num.replace(" ", "")
            Integer.parseInt(num1)
        }
    }

    fun driverDataUIState(): Boolean {
        return driversController.driversIsAllNew()
    }

    fun driverChangeState(position: Int, driverType: DriversType): DriversType {
        dataUIchangeListener.invoke(Unit)
        println(position.toString())
        return when (position) {
            0 -> {
                driversController.oneDriver = driverType
                driversController.oneDriver
            }
            1 -> {
                driversController.twoDriver = driverType

                driversController.twoDriver
            }
            2 -> {
                driversController.thirdDriver = driverType
                driversController.thirdDriver
            }
            3 -> {
                driversController.fourDriver = driverType
                driversController.fourDriver
            }
            else -> {
                driversController.fiveDriver = driverType
                driversController.fiveDriver
            }
        }
    }

    fun clearDriverList() = driverList.clear()
    fun ViewModel.requestConvertor(driver: Driver): DriverLocal = DriverLocal(
        licenseSeria = driver.license_seria,
        licenseNumber = driver.license_number,
        licenseDate = driver.license_date,
        firstNameLatin = driver.first_name_latin.toString(),
        passportNumber = driver.passport_number.toString(),
        lastNameLatin = driver.last_name_latin.toString(),
        address = null,
        num = driver.num,
        passportSeries = driver.passport_series,
        middleNameLatin = driver.middle_name_latin,
        fake = driver.fake,
        birthDate = driver.birth_date,
        document = driver.document,
        start_date = driver.start_date
    )

    fun ViewModel.requestConvertorDriverResponse(
        driver: DriverResponse,
        number: Int,
        driverPassSeries: String,
        driverPassNumber: String
    ): DriverLocal =
        DriverLocal(
            licenseSeria = driver.licenseSeria,
            licenseNumber = driver.licenseNumber,
            licenseDate = driver.licenseDate,
            firstNameLatin = driver.firstNameLatin.toString(),
            passportNumber = driverPassNumber.toString(),
            lastNameLatin = driver.lastNameLatin.toString(),
            address = null,
            num = number,
            passportSeries = driverPassSeries,
            middleNameLatin = driver.middleNameLatin,
            fake = false,
            birthDate = driver.birthDate,
            document = driver.document,
            start_date = driver.startDate
        )

    fun ViewModel.removeDriverLocal(driverRequest: DriverRemoveRequest) {
        driverList.forEach {
            if (it.passportSeries == it.passportSeries && it.passportNumber == driverRequest.removeDriverPassportNumber) {
                driverList.remove(it)
            }
        }
    }

    fun beforeAdded(driver: DriverRequest): DriverLocal? {
        var driverItemInList: DriverLocal? = null
        driverList.forEach {
            println(it.passportSeries)
            println(it.passportNumber)
            if (it.passportSeries.toString().equals(
                    driver.passportSeries,
                    ignoreCase = true
                ) && it.passportNumber.toString()
                    .equals(driver.passportNumber.toString(), ignoreCase = true)
            ) {
                driverItemInList = it
            }
        }
        return driverItemInList
    }

    fun ViewModel.addDriverLocal(driverLocal: DriverLocal): Boolean {
        return driverList.add(driverLocal)
    }

    fun ViewModel.getDriverList() = driverList
    lateinit var dataUIchangeListener: (Unit) -> Unit

    var positionDriverItem = 0;
    lateinit var listenAddProgress: (Boolean) -> Unit
    lateinit var addNewTab: (Unit) -> Unit
    fun setListenerAddNewTab(listener: (Unit) -> Unit){
        addNewTab=listener
    }

    fun setListenedProgress(listener: (Boolean) -> Unit) {
        listenAddProgress = listener
    }

    var removeDisable = true

    fun setDriverCountListener(listener: (Int) -> Unit) {
        setDriverCountListener = listener
    }


    fun setDataUIChangeListener(listener: (Unit) -> Unit) {
        dataUIchangeListener = listener
    }

    lateinit var vehicleResponse: GetVehicleResponse
    lateinit var currentScreenEnumRegisterLogin: CurrentScreenEnum
    var position = 0

    var currentPage = 0
    const val REQUEST_CODE = 0
    var isBuyOrRegistered: Boolean = false
    const val PERIOD_MS: Long = 2000

    fun loadContactUs(): ArrayList<ContactUsItem> {
        val arrayList = ArrayList<ContactUsItem>()
        arrayList.add(
            ContactUsItem(
                instance.getString(R.string.contact_us1),
                instance.getString(R.string.contact_us_all_description),
                true
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