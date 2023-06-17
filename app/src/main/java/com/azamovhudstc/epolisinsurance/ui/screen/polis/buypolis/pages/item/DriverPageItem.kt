package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.item

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
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
import com.azamovhudstc.epolisinsurance.utils.LocalData.change
import com.azamovhudstc.epolisinsurance.utils.LocalData.listenAddProgress
import com.azamovhudstc.epolisinsurance.utils.LocalData.position
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.utils.enums.ScrollType
import com.azamovhudstc.epolisinsurance.viewmodel.AddDriverViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AddDriverViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_driver_page_item.*


@AndroidEntryPoint
class DriverPageItem : Fragment(R.layout.fragment_driver_page_item) {
    private var passportSere = false
    private var passportNumber = false
    private var successDriver=false
    private var date = false
    private var selectedItemPosition = 0
    private var openCollapseDriver = false
    private val viewModel: AddDriverViewModel by viewModels<AddDriverViewModelImp>()
    private lateinit var removeItemClickListener: ((TabModel, Int) -> Unit)
    fun setRemoveClickListener(removeListener: ((TabModel, Int) -> Unit)) {
        removeItemClickListener = removeListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this){
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
            disableDriverInputs()
            successDriver=true
            listenAddProgress.invoke(false)
            driverLicenseSeries.setText(it.licenseSeria)
            line_driver_response_top.setBackgroundResource(R.drawable.line_bg)
            kinshipContainer.gone()
            driverLicenseNumber.setText(it.licenseNumber)
            change.invoke(ScrollType.FULL_HEIGHT)
            val layoutParams = delete_item.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.topToBottom=response_expanded_driver_item.id
            delete_item.layoutParams=layoutParams
            driverLicenseDate.setText(it.licenseDate)
            driver_fio.text =
                "${it.firstNameLatin.firstLetterUpper()} ${it.lastNameLatin.firstLetterUpper()} ${it.middleNameLatin.firstLetterUpper()}"
            response_expanded_driver_item.visible()
            driver_bornDate.setDefault()
            driver_passNumber.setDefault()
            driver_passSere.setDefaultSmall()
            errorDriverTxt.gone()

        }
        viewModel.removeDriverResponse.observe(this){
            println(it.removed)
            val data = arguments?.getSerializable("data") as TabModel
            removeItemClickListener.invoke(data, position!!)
            delete_item.onlyOneClick()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        driver_passSere.setText("")
        change.invoke(ScrollType.WRAP_HEIGHT)
        driver_passNumber.setText("")
        driver_passSere.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        driver_bornDate.setText(
            ""
        )



        driverKinship.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItemPosition = position
                println("Position :DDD :$position")
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
                    viewModel.addDriver(
                        DriverRequest(
                            driver_bornDate.text.toString(),
                            it.toString(),
                            driver_passNumber.text.toString(),
                            vehicleId = "297",
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
                passportNumber = true
                driver_bornDate.requestFocus()
                if (passportSere && passportNumber && date && selectedItemPosition > 0) {
                    disableDriverInputs()
                    val position = arguments?.getInt("position", 0)!! + 1
                    viewModel.addDriver(
                        DriverRequest(
                            driver_bornDate.text.toString(),
                            driver_passSere.text.toString(),
                            it.toString(),
                            vehicleId = "297",
                            position.toString()
                        )
                    )
                } else if (selectedItemPosition < 0) {
                    errorDriverTxt.visible()
                    kinshipContainer.vibrationAnimation()
                    error_text_driver.text = requireActivity().getString(R.string.maydon_empty)
                }
            } else {
                passportNumber = false

            }
        }
        driver_bornDate.addTextChangedListener {

            if (it.toString().length == 10) {
                date = true
                if (passportSere && passportNumber && date && selectedItemPosition > 0) {
                    disableDriverInputs()
                    val position = arguments?.getInt("position", 0)!! + 1
                    viewModel.addDriver(
                        DriverRequest(
                            it.toString(),
                            driver_passSere.text.toString(),
                            driver_passNumber.text.toString(),
                            vehicleId = "297",
                            position.toString()
                        )
                    )
                } else if (selectedItemPosition < 0) {

                    errorDriverTxt.visible()
                    kinshipContainer.vibrationAnimation()
                    error_text_driver.text = requireActivity().getString(R.string.maydon_empty)
                }
            } else {
                date = false

            }
        }
//        send_driver.setOnClickListener {
//            println("qweqweqwe")
//            if (driver_passSere.text.toString().isEmpty()) {
//                errorDriverTxt.visible()
//                error_text_driver.text = getString(R.string.passport_seria)
//                driver_passSere.vibrationAnimation()
//            } else if (driver_passNumber.text.toString().isEmpty()) {
//                driver_passNumber.vibrationAnimation()
//                errorDriverTxt.visible()
//                error_text_driver.text = getString(R.string.passport_number)
//
//            } else if (driver_bornDate.text.toString().isEmpty()) {
//                driver_bornDate.vibrationAnimation()
//                errorDriverTxt.visible()
//                error_text_driver.text = getString(R.string.empty_born_date)
//
//
//            } else {
//                if (selectedItemPosition > 0) {
//                    val position = arguments?.getInt("position", 0)!! + 1
//                    val driverRequest = DriverRequest(
//                        driver_bornDate.text.toString(),
//                        driver_passSere.text.toString(),
//                        driver_passNumber.text.toString(),
//                        vehicleId = "297",
//                        position.toString()
//                    )
//                    viewModel.addDriver(driverRequest)
//                } else {
//                    errorDriverTxt.visible()
//                    kinshipContainer.vibrationAnimation()
//                    error_text_driver.text = requireActivity().getString(R.string.maydon_empty)
//
//                }
//            }
//
//        }
        initView()

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
        response_expanded_driver_item.gone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("Tushdi")
    }

    override fun onResume() {
        super.onResume()
//        if (removeDisable) {
//            delete_item.gone()
//        } else {
//            delete_item.visible()
//        }
    }

    override fun onPause() {
        super.onPause()
//        if (removeDisable) {
//            delete_item.gone()
//        } else {
//            delete_item.visible()
//        }
    }

    private fun initView() {
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
        val position = arguments?.getInt("position", 0)

        //        delete_item.isEnabled = !removeDisable
//        delete_item.gone()


        delete_item.setOnClickListener {
            if (successDriver){
                clearData()
                delete_item.gone()
                viewModel.removeDriver(
                    DriverRemoveRequest(
                        driver_passNumber.text.toString(),
                        driver_passSere.text.toString(),
                        vehicleID = "297",
                        position.toString()
                    )
                )
            }
            else{

            }
        }
    }


}