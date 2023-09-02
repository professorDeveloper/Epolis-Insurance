package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.text.InputType
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitPolicyRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitRequest
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.LocalData.govNumber
import com.azamovhudstc.epolisinsurance.utils.LocalData.pollsPeopleType
import com.azamovhudstc.epolisinsurance.utils.LocalData.reformatDate
import com.azamovhudstc.epolisinsurance.utils.LocalData.stepViewController
import com.azamovhudstc.epolisinsurance.utils.LocalData.submitPolicyRequest
import com.azamovhudstc.epolisinsurance.utils.LocalData.vehicleId
import com.azamovhudstc.epolisinsurance.utils.LocalData.vehicleResponse
import com.azamovhudstc.epolisinsurance.utils.enums.AllInfoBtnType
import com.azamovhudstc.epolisinsurance.utils.enums.PollsPeopleType
import com.azamovhudstc.epolisinsurance.viewmodel.AllInfoPageViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AllInfoPageViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.all_info_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@AndroidEntryPoint
class AllInfoPage : Fragment(R.layout.all_info_page) {
    lateinit var selectedDate: LocalDate
    private val viewModel: AllInfoPageViewModel by viewModels<AllInfoPageViewModelImp>()
    private var openCollapseCar = false

    private var nextBtnType: AllInfoBtnType = AllInfoBtnType.Car
    private var openCollapseUser = false
    private var policyId = ""

    @SuppressLint("NewApi")
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
            userPassNumber.isEnabled = false
            userPassSerie.isEnabled = false
            polis_phone.requestFocus()
            clear_response_user.visible()
            phone_verify_user.visible()
            choose_polis_container.visible()
            date_polis.visible()
            nextBtnType = AllInfoBtnType.Next
        }
        viewModel.errorResponseLiveData.observe(this) {
            searchCarNumber.setError()
            searchCarTexNumber.setError()
            errorTxt.visible()
            error_text.text = it
            response_expanded.gone()
            searchCarTexSerie.setErrorSmall()
        }
        viewModel.submitForm1LiveData.observe(this) {
            vehicleId = it.vehicle.id
            submitPolicyRequest = SubmitPolicyRequest(
                it.owner.phone.replace(Regex("[^0-9]"), ""),
                it.policy.begin_date.replace('.', '-').reformatDate(),
                it.policy.id.toString()
            )
            do5PeopleContainer.isClickable = false
            do1PeopleContainer.isClickable = false
            do5PeopleContainer.isEnabled = false
            do1PeopleContainer.isEnabled = false
            polis_phone.isEnabled = false
            viewpagerChangeListener.invoke(1)
            start_polis_Date.isEnabled = false
            govNumber = it.vehicle.gov_number
            stepViewController.isOneDone = true
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
            userPassSerie.filters = arrayOf<InputFilter>(AllCaps())


            userPassSerie.requestFocus()
            vehicleResponse = it
            searched_user_named.text = it.result.owner
            searched_car_named.text = it.result.modelName
            address_searched_car.text = it.result.division
            searched_jsshshr.text = it.result.pinfl
            searched_issueYear.text = it.result.issueYear.toString().getYearCurrentLanguage()
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

    private fun clearUserData() {
        nextBtnType = AllInfoBtnType.User
        userPassNumber.isEnabled = true
        userPassSerie.isEnabled = true
        userPassSerie.isAllCaps = true
        userPassSerie.requestFocus()
        userPassSerie.text.clear()
        clear_response_user.gone()
        phone_verify_user.gone()
        choose_polis_container.gone()
        date_polis.gone()
        userPassNumber.text.clear()


    }

    private fun clearCarData() {
        choose_polis_container.gone()
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
        clear_response_user.setOnClickListener {
            clearUserData()
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
            if (userPassSerie.text.trim().isEmpty()) {
                userPassSerie.setErrorSmall()
                userPassSerie.vibrationAnimation()
                errorTxtUser.visible()
                error_user_text.text = getString(R.string.passport_seria)

            } else if (userPassNumber.text.trim().isEmpty()) {
                userPassNumber.setError()
                errorTxtUser.visible()
                error_user_text.text = getString(R.string.passport_number)
                userPassNumber.vibrationAnimation()

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
                        phone = "998${polis_phone.text.toString()}",
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
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val format = formatter.format(localDateTime)
        start_polis_Date.setText(format.toString())
        val nextYearDate = localDateTime.plusYears(1).minusDays(1)
        end_date.setText(formatter.format(nextYearDate))

        start_polis_Date.setOnClickListener {
            showDatePickerDialog()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDatePickerDialog() {
        if (::selectedDate.isInitialized) {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.CustomDatePickerDialog,
                { datePicker: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                    selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
                    val nextYearDate = selectedDate.plusYears(1).minusDays(1)
                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    end_date.setText(formatter.format(nextYearDate))
                    start_polis_Date.setText(formatter.format(selectedDate))
                },
                selectedDate.year,
                selectedDate.month.value,
                selectedDate.dayOfMonth
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()

            datePickerDialog.show()
        } else {
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.CustomDatePickerDialog,
                { datePicker: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                    selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
                    val nextYearDate = selectedDate.plusYears(1).minusDays(1)
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

    }

    private fun initContainers() {
        openCloseCollapseUserContainer.setOnClickListener {
            openCollapseUser = if (openCollapseUser) {
                open_user_info.setImageResource(R.drawable.collapse_open)
                expandedContainerUser.expandView()
                expandedContainerUser.visible()
                !openCollapseUser

            } else {
                open_user_info.setImageResource(R.drawable.form_icons)
                expandedContainer.gone()
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

    private fun initRequestFocus() {
        polis_phone.phoneMask()

        searchCarNumber.requestFocusOpeningScreen()
        searchCarNumber.requestFocus(length = 8, requestFocus = searchCarTexSerie)
        searchCarTexSerie.requestFocus(length = 3, requestFocus = searchCarTexNumber)
        searchCarTexNumber.requestFocus(length = 7, requestFocus = search_car)
        userPassSerie.requestFocus(length = 2, requestFocus = userPassNumber)
        userPassNumber.requestFocus(length = 7, requestFocus = search_car)
        polis_phone.requestFocus(length = 14, start_polis_Date)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_car.setOnClickListener {
            nextClick()
        }
        initRequestFocus()
        clearInitView()
        initContainers()
        initStartDate()
        initChoosePolls()
        searchCarNumber.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        searchCarTexSerie.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
    }
}