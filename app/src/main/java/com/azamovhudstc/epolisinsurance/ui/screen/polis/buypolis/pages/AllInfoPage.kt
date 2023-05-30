package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintAttribute
import androidx.constraintlayout.widget.ConstraintLayout
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
    private  var nextBtnType:AllInfoBtnType=AllInfoBtnType.Car
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
            phone_verify_user.visible()
            choose_polis_container.visible()
            date_polis.visible()
            clear_response_user.visible()
            nextBtnType=AllInfoBtnType.Next
        }
        viewModel.errorResponseLiveData.observe(this) {
            searchCarNumber.setError()
            searchCarTexNumber.setError()
            errorTxt.visible()
            response_expanded.gone()
            searchCarTexSerie.setErrorSmall()

        }
        viewModel.nextLiveData.observe(this){
            viewpagerChangeListener.invoke(1)
        }
        viewModel.errorByIdResponseLiveData.observe(this){
            userPassSerie.setErrorSmall()
            userPassNumber.setError()
            errorTxtUser.visible()
            phone_verify_user.gone()
            clear_response_user.gone()

        }
        viewModel.responseLiveData.observe(this) {
            response_expanded.visible()
            userContainer.visible()
            disableCarInputs()
            vehicleResponse=it
            searched_user_named.text=it.result.owner
            searched_car_named.text = it.result.modelName
            address_searched_car.text = it.result.division
            searched_jsshshr.text=it.result.pinfl
            searched_issueYear.text = it.result.issueYear.toString()
            nextBtnType=AllInfoBtnType.User
        }
    }
    private  fun  disableCarInputs(){
        searchCarNumber.isEnabled = false;
        searchCarTexNumber.isEnabled = false;
        searchCarTexSerie.isEnabled = false;
    }
    private fun  enableCarInputs(){
        searchCarNumber.isEnabled = true;
        searchCarTexNumber.isEnabled = true;
        searchCarTexSerie.isEnabled = true;

    }
    private fun clearCarData(){
        response_expanded.gone()
        enableCarInputs()
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
    private fun  clearInitView(){
        clear_response.setOnClickListener {
            clearCarData()
        }
    }
    private fun nextClick(){
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
            if (userPassNumber.text.isEmpty()||userPassSerie.text.isEmpty()){
                showSnack(userContainer,"Maydonlar bo`sh")
            }
            else{
                userPassSerie.setDefaultSmall()
                userPassNumber.setDefault()
                errorTxtUser.gone()
                viewModel.getUserData(PassportIdDataRequest(passportNumber =userPassNumber.text.toString() , passportSeries = userPassSerie.text.toString(), pinfl =vehicleResponse.result.pinfl , vehicle_id =vehicleResponse.result.vehicle_id.toString()))

            }
        }
        else if(nextBtnType==AllInfoBtnType.Next){
            viewModel.nextClick()
        }
    }
    private fun initContainers(){
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_car.setOnClickListener {
            nextClick()
        }
        clearInitView()
        initContainers()
    }

}