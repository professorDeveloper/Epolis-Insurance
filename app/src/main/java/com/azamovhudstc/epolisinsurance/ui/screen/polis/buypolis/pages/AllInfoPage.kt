package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.LocalData.vehicleResponse
import com.azamovhudstc.epolisinsurance.utils.enums.AllInfoBtnType
import com.azamovhudstc.epolisinsurance.viewmodel.AllInfoPageViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AllInfoPageViewModelImp
import com.azamovhudstc.sugurtaapp.utils.showSnack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_one_page.*


@AndroidEntryPoint
class AllInfoPage : Fragment(R.layout.fragment_one_page) {

    private val viewModel: AllInfoPageViewModel by viewModels<AllInfoPageViewModelImp>()
    private var openCollapseCar = false
    private  var nextBtnType:AllInfoBtnType=AllInfoBtnType.User
    private var openCollapseUser = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this) {
            if (it) {
                seach_car_progress.visible()
                seach_car_txt.gone()
            } else {

                seach_car_txt.visible()
                seach_car_progress.gone()
            }
        }
        viewModel.userDataResponseLiveData.observe(this){
            showSnack(message = "asdad")
        }
        viewModel.errorResponseLiveData.observe(this) {
            searchCarNumber.setError()
            searchCarTexNumber.setError()
            errorTxt.visible()
            response_expanded.gone()
            searchCarTexSerie.setErrorSmall()

        }
        viewModel.errorByIdResponseLiveData.observe(this){
            userPassSerie.setErrorSmall()
            userPassNumber.setError()
            errorTxtUser.visible()

        }
        viewModel.responseLiveData.observe(this) {
            response_expanded.visible()
            userContainer.visible()
            vehicleResponse=it

            searched_user_named.text=it.result.owner
            searched_car_named.text = it.result.modelName
            address_searched_car.text = it.result.division
            searched_jsshshr.text=it.result.pinfl
            searched_issueYear.text = it.result.issueYear.toString()
            nextBtnType=AllInfoBtnType.User
        }
    }
    private fun  userNameFormatter(string: String):String{
        val words = string.split(" ")
        val result = StringBuilder()

        for (word in words) {
            val firstChar = word[0]
            val restOfWord = word.substring(1).toLowerCase()
            val modifiedWord = firstChar.toLowerCase() + restOfWord
            result.append(modifiedWord).append(" ")
        }

        return  result.toString().trim()

    }
    private fun clearCarData(){
        response_expanded.gone()
        userContainer.gone()
        nextBtnType=AllInfoBtnType.Car
        searchCarNumber.text.clear()
        searchCarTexNumber.isClickable=true
        searchCarTexSerie.isClickable=true
        searchCarNumber.isClickable=true
        searchCarTexSerie.setDefaultSmall()
        searchCarNumber.setDefault()
        searchCarTexNumber.setDefault()

        searchCarTexSerie.text.clear()
        searchCarTexNumber.text.clear()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clear_response.setOnClickListener {
            clearCarData()
        }
        search_car.setOnClickListener {
            if (nextBtnType==AllInfoBtnType.Car){
                errorTxt.gone()
                searchCarTexSerie.setDefaultSmall()
                searchCarNumber.setDefault()
                searchCarTexNumber.setDefault()

                if (searchCarNumber.text.toString().isEmpty() ||
                    searchCarTexSerie.text.toString().isEmpty() ||
                    searchCarTexNumber.text.toString().isEmpty()
                ) {
                    showSnack(expandedContainer,"Maydonlar bo`sh", Toast.LENGTH_SHORT)
                } else {

                    viewModel.searchCar(
                        GetVehicleRequest(
                            searchCarTexSerie.text.toString().uppercase(),
                            searchCarTexNumber.text.toString(),
                            searchCarNumber.text.toString().uppercase(),
                        )
                    )
                }
            }
            else if (nextBtnType==AllInfoBtnType.User){
                userPassSerie.setDefaultSmall()
                userPassNumber.setDefault()
                errorTxtUser.gone()
                viewModel.getUserData(PassportIdDataRequest(passportNumber =userPassNumber.text.toString() , passportSeries = userPassSerie.text.toString(), pinfl =vehicleResponse.result.pinfl , vehicle_id =vehicleResponse.result.vehicle_id.toString()))
            }
        }
        openCloseCollapseUserContainer.setOnClickListener {
            openCollapseUser = if (openCollapseUser) {
                open_user_info.setImageResource(R.drawable.collapse_open)
                expandedContainerUser.visible()
                !openCollapseUser

            } else {
                open_car_info.setImageResource(R.drawable.form_icons)
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

}