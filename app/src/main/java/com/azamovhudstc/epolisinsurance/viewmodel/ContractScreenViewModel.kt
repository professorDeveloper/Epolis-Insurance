package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitPolicyRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.policy.SubmitPolicyResponse

interface ContractScreenViewModel {
    fun submitPolicy(policyRequest: SubmitPolicyRequest)
    val submitPolicyResponseLiveData:MutableLiveData<SubmitPolicyResponse>
    val progressLiveData:MutableLiveData<Boolean>
    val  errorResponseLiveData:MutableLiveData<String>
}