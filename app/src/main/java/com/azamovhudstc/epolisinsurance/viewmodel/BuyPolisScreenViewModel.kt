package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetTechPassResoponse

interface BuyPolisScreenViewModel {
    val responseLiveData:MutableLiveData<GetTechPassResoponse>
    val errorResponseLiveData:MutableLiveData<String>
    val progressLiveData:MutableLiveData<Boolean>
    fun searchCar(request: SearchCarAndGetPassRequest)
}