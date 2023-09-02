package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData

interface InfoDriverViewModel {
    fun nextBtn()
     val    isNextBtnSuccessLiveData:MutableLiveData<Boolean>
}