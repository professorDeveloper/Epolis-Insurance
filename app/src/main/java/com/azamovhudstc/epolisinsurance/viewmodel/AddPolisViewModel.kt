package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData

interface AddPolisViewModel{
    val addSpinnerLiveData:MutableLiveData<ArrayList<String>>
    fun getSpinnerData()

}