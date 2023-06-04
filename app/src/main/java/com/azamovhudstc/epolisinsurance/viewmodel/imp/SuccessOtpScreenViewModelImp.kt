package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.viewmodel.SuccessOtpScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuccessOtpScreenViewModelImp  : SuccessOtpScreenViewModel, ViewModel() {
    override val clickLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun onClick() {
        viewModelScope.launch {

            progressLiveData.value = true
            delay(800)
            clickLiveData.value=Unit
            progressLiveData.value=false
        }
    }
}