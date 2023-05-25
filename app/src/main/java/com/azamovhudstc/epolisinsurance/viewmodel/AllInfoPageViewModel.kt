package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse

interface AllInfoPageViewModel {
    val responseLiveData:MutableLiveData<GetVehicleResponse>
    val errorResponseLiveData:MutableLiveData<String>
    val errorByIdResponseLiveData:MutableLiveData<String>
    val userDataResponseLiveData:MutableLiveData<GetUserDataByIdResponse>
    val progressLiveData:MutableLiveData<Boolean>
    fun getUserData(request: PassportIdDataRequest)
    fun searchCar(request: GetVehicleRequest)
}