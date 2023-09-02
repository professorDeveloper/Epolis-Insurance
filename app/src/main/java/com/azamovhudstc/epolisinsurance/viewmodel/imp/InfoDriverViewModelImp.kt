package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamovhudstc.epolisinsurance.utils.LocalData.driverDataUIState
import com.azamovhudstc.epolisinsurance.utils.LocalData.pollsPeopleType
import com.azamovhudstc.epolisinsurance.utils.enums.PollsPeopleType
import com.azamovhudstc.epolisinsurance.viewmodel.InfoDriverViewModel

class InfoDriverViewModelImp : InfoDriverViewModel, ViewModel() {
    override fun nextBtn() {
        if (pollsPeopleType==PollsPeopleType.PremiumPolls){
            isNextBtnSuccessLiveData.value=true
        }
        else{
            isNextBtnSuccessLiveData.value=driverDataUIState()
        }
    }

    override val isNextBtnSuccessLiveData: MutableLiveData<Boolean> = MutableLiveData()

}