package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData

interface OnBoardingViewModel {
    val progressLiveData:MutableLiveData<Boolean>
    val resultLiveData:MutableLiveData<Unit>
    fun next()
}