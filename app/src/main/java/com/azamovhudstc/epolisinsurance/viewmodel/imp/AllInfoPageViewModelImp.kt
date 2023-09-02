package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.SubmitForm1Response
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse
import com.azamovhudstc.epolisinsurance.usecase.AllInfoScreenUseCase
import com.azamovhudstc.epolisinsurance.utils.LocalData.addDriverLocal
import com.azamovhudstc.epolisinsurance.utils.LocalData.clearDriverList
import com.azamovhudstc.epolisinsurance.utils.LocalData.getDriverList
import com.azamovhudstc.epolisinsurance.utils.LocalData.requestConvertor
import com.azamovhudstc.epolisinsurance.utils.LocalData.vehicleId
import com.azamovhudstc.epolisinsurance.viewmodel.AllInfoPageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AllInfoPageViewModelImp @Inject constructor(private val allInfoScreenUseCase: AllInfoScreenUseCase) :
    AllInfoPageViewModel, ViewModel() {


    override val submitForm1LiveData: MutableLiveData<SubmitForm1Response> = MutableLiveData()
    override fun submitForm1(submitRequest: SubmitRequest) {
        progressLiveData.value = true
        allInfoScreenUseCase.submitForm1(submitRequest).onEach {
            it.onSuccess { value: SubmitForm1Response ->
                vehicleId = value.vehicle.id
                if (!value.all_drivers_fake) {
                    clearDriverList()
                    value.drivers.forEach { driver ->
                        if (!driver.fake) {
                            addDriverLocal(requestConvertor(driver))
                        }
                    }
                    getDriverList().forEach {
                        println(it.passportSeries)
                        println(it.passportNumber)
                        println(it.num)
                        println(it.licenseNumber)
                    }
                }
                submitForm1LiveData.value = value
                progressLiveData.value = false

            }
            it.onFailure { exception: Throwable ->
                errorSubmitForm1Response.value = exception.message
                progressLiveData.value = false
            }
        }.launchIn(viewModelScope)
    }

    override val responseLiveData: MutableLiveData<GetVehicleResponse> = MutableLiveData()
    override val errorSubmitForm1Response: MutableLiveData<String> = MutableLiveData()
    override val errorResponseLiveData: MutableLiveData<String> = MutableLiveData()
    override val errorByIdResponseLiveData: MutableLiveData<String> = MutableLiveData()
    override val userDataResponseLiveData: MutableLiveData<GetUserDataByIdResponse> =
        MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override fun getUserData(request: PassportIdDataRequest) {
        progressLiveData.value = true
        allInfoScreenUseCase.getUserDataByID(request).onEach {
            it.onSuccess { value: GetUserDataByIdResponse ->
                userDataResponseLiveData.value = value
                progressLiveData.value = false
            }
            it.onFailure { exception: Throwable ->

                errorByIdResponseLiveData.value = exception.message
                progressLiveData.value = false


            }
        }.launchIn(viewModelScope)
    }

    override fun searchCar(request: GetVehicleRequest) {
        progressLiveData.value = true
        allInfoScreenUseCase.getTechDataByID(request).onEach { result ->
            result.onSuccess { value: GetVehicleResponse ->
                responseLiveData.value = value
                progressLiveData.value = false
            }
            result.onFailure { exception: Throwable ->
                errorResponseLiveData.value = exception.message
                progressLiveData.value = false
            }

        }.launchIn(viewModelScope)
    }
}