package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRemoveRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.RemovedSuccessData

interface AddDriverViewModel {
    fun addDriver(driverRequest: DriverRequest)
    fun removeDriver(driverRemoveRequest: DriverRemoveRequest)
    val removeDriverResponse:MutableLiveData<RemovedSuccessData>
    val driverResponseLiveData:MutableLiveData<DriverResponse>
    val progressLiveData:MutableLiveData<Boolean>
    val  errorResponseLiveData:MutableLiveData<String>

}