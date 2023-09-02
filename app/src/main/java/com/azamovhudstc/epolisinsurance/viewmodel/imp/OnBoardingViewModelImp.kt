package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OnBoardingViewModelImp : OnBoardingViewModel,ViewModel() {
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val resultLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun next() {
        viewModelScope.launch {
            progressLiveData.value=true
            delay(700)
            progressLiveData.value=false
            resultLiveData.value=Unit
        }

    }
}