package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.SubmitForm1Response
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse

interface AllInfoPageViewModel {
    val submitForm1LiveData:MutableLiveData<SubmitForm1Response>
    fun submitForm1(submitRequest: SubmitRequest)
    val responseLiveData:MutableLiveData<GetVehicleResponse>
    val errorSubmitForm1Response:MutableLiveData<String>
    val errorResponseLiveData:MutableLiveData<String>
    val errorByIdResponseLiveData:MutableLiveData<String>
    val userDataResponseLiveData:MutableLiveData<GetUserDataByIdResponse>
    val progressLiveData:MutableLiveData<Boolean>
    fun getUserData(request: PassportIdDataRequest)
    fun searchCar(request: GetVehicleRequest)
}