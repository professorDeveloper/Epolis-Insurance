package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.remote.request.ConfirmRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.register.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.register.RegisterResponse

interface AuthViewModel {
    val progressLiveData: MutableLiveData<Boolean>
    val confirmResponseLiveData:MutableLiveData<ConfirmResponse>
    val registerResponseLiveData: MutableLiveData<RegisterResponse>
    val errorResponseLiveData: MutableLiveData<String>
    fun confirmOtp(confirmRequest: ConfirmRequest)
    fun registerUser(registerRequest: RegisterRequest)

}