package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRemoveRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.RemovedSuccessData
import com.azamovhudstc.epolisinsurance.usecase.AddDriverUseCase
import com.azamovhudstc.epolisinsurance.utils.LocalData.addDriverLocal
import com.azamovhudstc.epolisinsurance.utils.LocalData.beforeAdded
import com.azamovhudstc.epolisinsurance.utils.LocalData.removeDriverLocal
import com.azamovhudstc.epolisinsurance.utils.LocalData.requestConvertorDriverResponse
import com.azamovhudstc.epolisinsurance.viewmodel.AddDriverViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddDriverViewModelImp @Inject constructor(private val addDriverUseCase: AddDriverUseCase) :
    AddDriverViewModel, ViewModel() {
    override val driverResponseLiveData: MutableLiveData<DriverResponse> = MutableLiveData()
    override val initDriverLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override val errorResponseLiveData: MutableLiveData<String> = MutableLiveData()
    override fun addDriver(driverRequest: DriverRequest) {
        progressLiveData.value = true
        println("OnViewModel")
        val beforeAdded = beforeAdded(driverRequest)

        if (beforeAdded == null) {
            addDriverUseCase.getDriverData(driverRequest).onEach {
                it.onFailure {
                    errorResponseLiveData.value = it.message
                    progressLiveData.value = false

                }
                it.onSuccess { value: DriverResponse ->
                    driverResponseLiveData.value = value
                    addDriverLocal(
                        requestConvertorDriverResponse(
                            value,
                            driverRequest.driverNum.toInt(),
                            driverRequest.passportSeries,
                            driverRequest.passportNumber
                        )

                    )

                    progressLiveData.value = false
                }
            }.launchIn(viewModelScope)
        }
        else{
            progressLiveData.value=false
            driverResponseLiveData.value=beforeAdded.convertorDriverResponse()
        }

    }

    override fun initDrivers() {

    }


    override fun removeDriver(driverRemoveRequest: DriverRemoveRequest) {
        progressLiveData.value = true
        addDriverUseCase.removeDriver(driverRemoveRequest).onEach { result ->
            result.onSuccess {
                println("Removing::::::::::::$driverRemoveRequest")
                removeDriverResponse.value = it
                progressLiveData.value = false

                removeDriverLocal(driverRemoveRequest)
            }
            result.onFailure {
                errorResponseLiveData.value = it.message
                println(it.message)
                progressLiveData.value = false

            }
        }.launchIn(viewModelScope)
    }


    override val removeDriverResponse: MutableLiveData<RemovedSuccessData> = MutableLiveData()

}