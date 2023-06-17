package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitRequest
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.LocalData.pollsPeopleType
import com.azamovhudstc.epolisinsurance.utils.LocalData.vehicleResponse
import com.azamovhudstc.epolisinsurance.utils.enums.AllInfoBtnType
import com.azamovhudstc.epolisinsurance.utils.enums.PollsPeopleType
import com.azamovhudstc.epolisinsurance.viewmodel.AllInfoPageViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AllInfoPageViewModelImp
import com.azamovhudstc.sugurtaapp.utils.showSnack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_one_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@AndroidEntryPoint
class AllInfoPage : Fragment(R.layout.fragment_one_page) {

    private val viewModel: AllInfoPageViewModel by viewModels<AllInfoPageViewModelImp>()
    private var openCollapseCar = false

    private var nextBtnType: AllInfoBtnType = AllInfoBtnType.Car
    private var openCollapseUser = false
    private var isCarNumberCorrect = false
    private var isCarSeriesCorrect = false
    private var policyId =""
    private var isCarPassportNumberCorrect = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        viewModel.errorSubmitForm1Response.observe(this) {
            showSnack(message = it)
        }
        viewModel.progressLiveData.observe(this) {
            if (it) {
                seach_car_progress.visible()
                seach_car_txt.gone()
            } else {

                seach_car_txt.visible()
                seach_car_progress.gone()
            }
        }
        viewModel.userDataResponseLiveData.observe(this) {
            phone_verify_user.visible()
            choose_polis_container.visible()
            date_polis.visible()
            userPassNumber.isEnabled = false
            userPassSerie.isEnabled = false
            clear_response_user.visible()
            nextBtnType = AllInfoBtnType.Next
        }
        viewModel.errorResponseLiveData.observe(this) {
            searchCarNumber.setError()
            searchCarTexNumber.setError()
            errorTxt.visible()
            error_text.text = it
            response_expanded.gone()
            searchCarTexSerie.setErrorSmall()
            coroutineScope.launch {
                delay(5000)
                searchCarNumber.setDefaultBig()
                searchCarTexNumber.setDefault()
                searchCarTexSerie.setDefaultSmall()

                errorTxt.gone()
            }

        }
        viewModel.submitForm1LiveData.observe(this) {
            open_user_info.setImageResource(R.drawable.form_icons)
            expandedContainerUser.gone()
            open_car_info.setImageResource(R.drawable.form_icons)
            expandedContainer.gone()
            viewpagerChangeListener.invoke(1)
        }
        viewModel.errorByIdResponseLiveData.observe(this) {
            userPassSerie.setErrorSmall()
            userPassNumber.setError()
            errorTxtUser.visible()
            phone_verify_user.gone()
            clear_response_user.gone()

        }
        viewModel.responseLiveData.observe(this) {
            response_expanded.visible()
            userContainer.visible()
            policyId = it.result.policy_id.toString()
            disableCarInputs()
            vehicleResponse = it
            searched_user_named.text = it.result.owner
            searched_car_named.text = it.result.modelName
            address_searched_car.text = it.result.division
            searched_jsshshr.text = it.result.pinfl
            searched_issueYear.text = it.result.issueYear.toString()
            nextBtnType = AllInfoBtnType.User
        }
    }

    private fun initChoosePolls() {
        var peopleIsFive = false
        if (peopleIsFive) {
            do5PeopleContainer.setBackgroundResource(R.drawable.choose_polis_bg_selected_end)
            do1PeopleContainer.setBackgroundResource(R.drawable.choose_polis_bg_unselected_start)
        } else {
            do5PeopleContainer.setBackgroundResource(R.drawable.choose_polis_bg_selected_start)
            do1PeopleContainer.setBackgroundResource(R.drawable.choose_polis_bg_unselected_end)
        }
        do5PeopleContainer.setOnClickListener {
            if (peopleIsFive) {
                peopleIsFive = false
                pollsPeopleType = PollsPeopleType.CustomPolis
                do5PeopleContainer.setBackgroundResource(R.drawable.choose_polis_bg_selected_start)
                do1PeopleContainer.setBackgroundResource(R.drawable.choose_polis_bg_unselected_end)
                do1Peopletxt.setTextColor(Color.BLACK)
                do5Peopletxt.setTextColor(Color.WHITE)
            }
        }
        do1PeopleContainer.setOnClickListener {
            if (!peopleIsFive) {
                peopleIsFive = true
                pollsPeopleType = PollsPeopleType.PremiumPolls

                do5Peopletxt.setTextColor(Color.BLACK)
                do1Peopletxt.setTextColor(Color.WHITE)
                do5PeopleContainer.setBackgroundResource(R.drawable.choose_polis_bg_unselected_start)
                do1PeopleContainer.setBackgroundResource(R.drawable.choose_polis_bg_selected_end)
            }
        }


    }

    private fun disableCarInputs() {
        searchCarNumber.isEnabled = false;
        searchCarTexNumber.isEnabled = false;
        searchCarTexSerie.isEnabled = false;
    }

    private fun enableCarInputs() {
        searchCarNumber.isEnabled = true;
        searchCarTexNumber.isEnabled = true;
        searchCarTexSerie.isEnabled = true;

    }

    private fun clearCarData() {
        response_expanded.gone()
        enableCarInputs()
        userContainer.gone()
        nextBtnType = AllInfoBtnType.Car
        searchCarNumber.text.clear()
        searchCarTexNumber.isClickable = true
        searchCarTexSerie.isClickable = true
        searchCarNumber.isClickable = true
        searchCarTexSerie.setDefaultSmall()
        searchCarNumber.setDefaultBig()
        searchCarTexNumber.setDefault()

        searchCarTexSerie.text.clear()
        searchCarTexNumber.text.clear()
    }

    private fun clearInitView() {
        clear_response.setOnClickListener {
            clearCarData()
        }
    }

    private fun nextClick() {
        if (nextBtnType == AllInfoBtnType.Car) {
            errorTxt.gone()
            searchCarTexSerie.setDefaultSmall()
            searchCarNumber.setDefaultBig()
            searchCarTexNumber.setDefault()


            if (searchCarNumber.text.toString().isEmpty()) {
                searchCarNumber.vibrationAnimation()
                errorTxt.visible()
                error_text.text = requireActivity().getString(R.string.car_number)


            } else if (searchCarTexSerie.text.toString().isEmpty()) {
                errorTxt.visible()
                error_text.text = getString(R.string.car_passport_seria)
                searchCarTexSerie.vibrationAnimation()
            } else if (searchCarTexNumber.text.toString().isEmpty()) {
                searchCarTexNumber.vibrationAnimation()
                errorTxt.visible()
                error_text.text = getString(R.string.car_number)

            } else {
                viewModel.searchCar(
                    GetVehicleRequest(
                        searchCarTexSerie.text.toString().uppercase(),
                        searchCarTexNumber.text.toString(),
                        searchCarNumber.text.toString().uppercase(),
                    )
                )
            }

        } else if (nextBtnType == AllInfoBtnType.User) {
            if (userPassNumber.text.isEmpty() || userPassSerie.text.isEmpty()) {
                showSnack(userContainer, getString(R.string.maydon_empty))
            } else {
                userPassSerie.setDefaultSmall()
                userPassNumber.setDefault()
                errorTxtUser.gone()
                viewModel.getUserData(
                    PassportIdDataRequest(
                        passportNumber = userPassNumber.text.toString(),
                        passportSeries = userPassSerie.text.toString(),
                        pinfl = vehicleResponse.result.pinfl,
                        vehicle_id = vehicleResponse.result.vehicle_id.toString()
                    )
                )

            }
        } else if (nextBtnType == AllInfoBtnType.Next) {
            if (polis_phone.text.toString().trim().isEmpty()) {
                polis_phone.vibrationAnimation()
            } else {
                viewModel.submitForm1(
                    SubmitRequest(
                        phone = polis_phone.unMaskedText.toString(),
                        beginDate = start_polis_Date.text.toString(),
                        policyId.toString(),
                        pollsPeopleType.polisType.toString()
                    )
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initStartDate() {
        var localDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val format = formatter.format(localDateTime)
        start_polis_Date.setText(format.toString())
        start_polis_Date.setOnClickListener {
            showDatePickerDialog()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDatePickerDialog() {
        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.CustomDatePickerDialog,
            { datePicker: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
                val nextYearDate = selectedDate.plusYears(1)
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                end_date.setText(formatter.format(nextYearDate))
                start_polis_Date.setText(formatter.format(selectedDate))
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()

        datePickerDialog.show()
    }

    private fun initContainers() {
        openCloseCollapseUserContainer.setOnClickListener {
            openCollapseUser = if (openCollapseUser) {
                open_user_info.setImageResource(R.drawable.collapse_open)
                expandedContainerUser.visible()
                !openCollapseUser

            } else {
                open_user_info.setImageResource(R.drawable.form_icons)
                expandedContainerUser.gone()
                !openCollapseUser
            }

        }
        openAboutCarCollapse.setOnClickListener {
            openCollapseCar = if (openCollapseCar) {
                open_car_info.setImageResource(R.drawable.collapse_open)
                expandedContainer.visible()
                !openCollapseCar

            } else {
                open_car_info.setImageResource(R.drawable.form_icons)
                expandedContainer.gone()
                !openCollapseCar
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_car.setOnClickListener {
            nextClick()
        }
        clearInitView()
        initContainers()
        initStartDate()
        initChoosePolls()
        searchCarNumber.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        searchCarTexSerie.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

//        listenEditText()

    }


}