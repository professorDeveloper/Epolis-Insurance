package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse

interface BuyPolisScreenViewModel {
    val responseLiveData:MutableLiveData<GetVehicleResponse>
    val errorResponseLiveData:MutableLiveData<String>
    val progressLiveData:MutableLiveData<Boolean>
    fun searchCar(request: GetVehicleRequest)
}