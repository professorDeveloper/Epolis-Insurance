package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData

interface SuccessOtpScreenViewModel {
    val clickLiveData:MutableLiveData<Unit>
    val progressLiveData:MutableLiveData<Boolean>
    fun onClick()
}