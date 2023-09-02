package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.item

import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRemoveRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRequest
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.LocalData.addNewTab
import com.azamovhudstc.epolisinsurance.utils.LocalData.driverChangeState
import com.azamovhudstc.epolisinsurance.utils.LocalData.listenAddProgress
import com.azamovhudstc.epolisinsurance.utils.LocalData.vehicleId
import com.azamovhudstc.epolisinsurance.utils.enums.DriversType
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.utils.enums.PollsPeopleType
import com.azamovhudstc.epolisinsurance.viewmodel.AddDriverViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AddDriverViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_driver_page_item.*


@AndroidEntryPoint
class DriverPageItem :
    Fragment(R.layout.fragment_driver_page_item) {
    private var passportSere = false
    private var passportNumber = false
    private var localPosition = 0
    private var successDriver = false
    private var date = false
    private var isRealPositionDriver = 0
    private var removePassSeries = ""
    private var removePassNumber = ""
    private var selectedItemPosition = 0
    private val viewModel: AddDriverViewModel by viewModels<AddDriverViewModelImp>()
    private lateinit var removeItemClickListener: ((TabModel, Int) -> Unit)
    fun setRemoveClickListener(removeListener: ((TabModel, Int) -> Unit)) {
        removeItemClickListener = removeListener
    }

    private var isFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this) {
            listenAddProgress.invoke(it)
        }
        viewModel.errorResponseLiveData.observe(this) {
            listenAddProgress.invoke(false)
            driver_bornDate.setError()
            driver_passNumber.setError()
            kinshipContainer.visible()
            errorDriverTxt.visible()
            error_text_driver.text = it
            line_driver_response_top.setBackgroundColor(Color.WHITE)
            enableCarInputs()
            response_expanded_driver_item.gone()
            driver_passSere.setErrorSmall()
            errorDriverTxt.vibrationAnimation()
            driver_passSere.vibrationAnimation()
            driver_passNumber.vibrationAnimation()
            driver_bornDate.vibrationAnimation()

        }
        viewModel.driverResponseLiveData.observe(this) {
            println("salom")
            println()
            isRealPositionDriver = it.num ?: 0
            disableDriverInputs()
            successDriver = true

            add_new_driver.visibility=View.VISIBLE
            add_new_driver.slideUp(600,0)
            removePassNumber = driver_passNumber.text.toString()
            removePassSeries = driver_passSere.text.toString()
            listenAddProgress.invoke(false)
            driverLicenseSeries.setText(it.licenseSeria)
            line_driver_response_top.setBackgroundResource(com.azamovhudstc.epolisinsurance.R.drawable.line_bg)
            kinshipContainer.gone()
            driverLicenseNumber.setText(it.licenseNumber)
            val layoutParams = delete_item.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.topToBottom = response_expanded_driver_item.id
            delete_item.layoutParams = layoutParams
            driverLicenseDate.setText(it.licenseDate)
            driver_fio.text =
                "${it.firstNameLatin!!.firstLetterUpper()} ${it.lastNameLatin!!.firstLetterUpper()} ${it.middleNameLatin!!.firstLetterUpper()}"
            response_expanded_driver_item.visible()
            driver_bornDate.setDefault()
            driver_passNumber.setDefault()
            driver_passSere.setDefaultSmall()
            errorDriverTxt.gone()

            val positionNum = arguments?.getInt("position", 0)!!
            driverChangeState(positionNum.toInt(), driverType = DriversType.DONE)
        }
        viewModel.removeDriverResponse.observe(this) {
            successDriver = false
            add_new_driver.slideUp(400,0)

            add_new_driver.visibility=View.GONE

            clearData()
            if (isRealPositionDriver!=0){
                driverChangeState(isRealPositionDriver, driverType = DriversType.NEW)
            }
            else{
                val positionNum = arguments?.getInt("position", 0)!!
                driverChangeState(positionNum, driverType = DriversType.NEW)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoDriverItemContainer.slideStart(800, 0)

        initView()
        driverKinship.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItemPosition = position
                driver_bornDate.requestFocus()
                if (passportSere && passportNumber && date && selectedItemPosition > 0) {
                    disableDriverInputs()
                    val positionNum = arguments?.getInt("position", 0)!! + 1
                    localPosition = positionNum
                    viewModel.addDriver(
                        DriverRequest(
                            driver_bornDate.text.toString(),
                            driver_passSere.text.toString(),
                            driver_passNumber.text.toString(),
                            vehicleId = vehicleId.toString(),
                            driverNum = positionNum.toString(),

                            selectedItemPosition.toString()
                        )
                    )
                }

                println("Position :DDD :$position")
                get_kinship.setText(driverKinship.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        driver_passSere.addTextChangedListener {
            if (it.toString().length == 2) {
                passportSere = true
                driver_passNumber.requestFocus()
                if (passportSere && passportNumber && date && selectedItemPosition > 0) {
                    disableDriverInputs()
                    val position = arguments?.getInt("position", 0)!! + 1
                    localPosition = position
                    viewModel.addDriver(
                        DriverRequest(
                            driver_bornDate.text.toString(),
                            it.toString(),
                            driver_passNumber.text.toString(),
                            vehicleId = vehicleId.toString(),
                            driverNum = position.toString(),
                            selectedItemPosition.toString()
                        )
                    )
                }
            } else {
                passportSere = false
            }
        }
        driver_passNumber.addTextChangedListener {
            if (it.toString().length == 7) {
                driverKinship.requestFocus()
                passportNumber = true
                if (passportSere && passportNumber && date && selectedItemPosition > 0) {
                    disableDriverInputs()
                    val position = arguments?.getInt("position", 0)!! + 1
                    localPosition = position
                    viewModel.addDriver(
                        DriverRequest(
                            it.toString(),
                            driver_passSere.text.toString(),
                            driver_passNumber.text.toString(),
                            vehicleId = vehicleId.toString(),
                            driverNum = position.toString(),
                            selectedItemPosition.toString()
                        )
                    )

                }
            } else {
                passportNumber = false

            }
        }
        driver_bornDate.addTextChangedListener {
            if (LocalData.pollsPeopleType == PollsPeopleType.PremiumPolls) {
                if (isFirstTime) {
                    isFirstTime = false;
                } else if (!isFirstTime) {
                    isFirstTime = true
                    val birthDate = it.toString()
                    if (birthDate.length == 10) {
                        date = true
                        if (passportSere && passportNumber && date && selectedItemPosition > 0) {
                            disableDriverInputs()
                            val position = arguments?.getInt("position", 0)!! + 1
                            localPosition = position
                            viewModel.addDriver(
                                DriverRequest(
                                    driver_bornDate.text.toString(),
                                    driver_passSere.text.toString(),
                                    driver_passNumber.text.toString(),
                                    vehicleId = vehicleId.toString(),
                                    driverNum = position.toString(),
                                    selectedItemPosition.toString()
                                )
                            )
                        } else if (selectedItemPosition <= 0) {
                            errorDriverTxt.visible()
                            kinshipContainer.vibrationAnimation()
                            error_text_driver.text =
                                requireActivity().getString(R.string.maydon_empty)
                        } else if (!passportSere) {
                            errorDriverTxt.visible()
                            driver_passSere.vibrationAnimation()
                            error_text_driver.text =
                                requireActivity().getString(R.string.maydon_empty)
                        } else if (!passportNumber) {
                            errorDriverTxt.visible()
                            driver_passNumber.vibrationAnimation()
                            error_text_driver.text =
                                requireActivity().getString(R.string.maydon_empty)

                        }
                    } else {
                        date = false
                    }
                }
            } else {
                if (isFirstTime) {
                    isFirstTime = false;
                } else if (!isFirstTime) {
                    isFirstTime = true
                    val birthDate = it.toString()
                    if (birthDate.length == 10) {
                        date = true
                        if (passportSere && passportNumber && date) {
                            disableDriverInputs()
                            val position = arguments?.getInt("position", 0)!! + 1
                            localPosition = position
                            viewModel.addDriver(
                                DriverRequest(
                                    driver_bornDate.text.toString(),
                                    driver_passSere.text.toString(),
                                    driver_passNumber.text.toString(),
                                    vehicleId = vehicleId.toString(),
                                    driverNum = position.toString(),
                                    selectedItemPosition.toString()
                                )
                            )
                        } else if (!passportSere) {
                            errorDriverTxt.visible()
                            driver_passSere.vibrationAnimation()
                            error_text_driver.text =
                                requireActivity().getString(R.string.maydon_empty)
                        } else if (!passportNumber) {
                            errorDriverTxt.visible()
                            driver_passNumber.vibrationAnimation()
                            error_text_driver.text =
                                requireActivity().getString(R.string.maydon_empty)

                        }
                    } else {
                        date = false
                    }
                }
            }

//            }}
        }

        delete_item.setSafeOnClickListener {
            if (successDriver) {
                if (isRealPositionDriver != 0) {
                    viewModel.removeDriver(
                        DriverRemoveRequest(
                            removePassNumber.toString(),
                            removePassSeries,
                            vehicleID = vehicleId.toString(),
                            isRealPositionDriver.toString()
                        )
                    )
                } else {
                    viewModel.removeDriver(
                        DriverRemoveRequest(
                            removePassNumber.toString(),
                            removePassSeries,
                            vehicleID = vehicleId.toString(),
                            localPosition.toString()
                        )
                    )
                }
            } else {
                clearData()
                delete_item.onlyOneClick()
                listenAddProgress.invoke(false)

            }
        }

    }

    private fun disableDriverInputs() {
        driver_bornDate.isEnabled = false;
        driver_passNumber.isEnabled = false;
        driver_passSere.isEnabled = false;
    }

    private fun enableCarInputs() {
        driver_bornDate.isEnabled = true;
        driver_passNumber.isEnabled = true;
        driver_passSere.isEnabled = true;

    }

    private fun clearData() {
        enableCarInputs()
        driver_bornDate.setText("")
        driver_passNumber.setText("")
        driver_passSere.setText("")
        removePassSeries = ""
        kinshipContainer.visible()
        removePassNumber = ""
        response_expanded_driver_item.gone()
    }


    private fun initView() {
        driver_passSere.filters = arrayOf<InputFilter>(InputFilter.AllCaps())
        add_new_driver.setSafeOnClickListener {
            addNewTab.invoke(Unit)
        }
        driver_passSere.requestFocusOpeningScreen()
        val data = arguments?.getSerializable("data") as TabModel
        val shp = AppReference(requireContext())
        when (shp.currentLanguage) {
            LanguageType.uz -> {
                driver_id_title.text = "${data.name}-Haydovchi"

            }
            LanguageType.ru -> {
                driver_id_title.text = "${data.name}-Водитель"

            }
        }
    }

}