package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitPolicyRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.policy.SubmitPolicyResponse
import com.azamovhudstc.epolisinsurance.usecase.ContactScreenUseCase
import com.azamovhudstc.epolisinsurance.viewmodel.ContractScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContractScreenViewModelImp @Inject constructor(private val contactScreenUseCase: ContactScreenUseCase) :
    ContractScreenViewModel, ViewModel() {
    override fun submitPolicy(policyRequest: SubmitPolicyRequest) {
        progressLiveData.value = true
        contactScreenUseCase.submitPolicy(submitPolicyRequest = policyRequest).onEach {
            it.onSuccess {
                submitPolicyResponseLiveData.value = it
                progressLiveData.value = false
            }
            it.onFailure {
                errorResponseLiveData.value = it.message
                progressLiveData.value = false
            }
        }.launchIn(viewModelScope)
    }

    override val submitPolicyResponseLiveData: MutableLiveData<SubmitPolicyResponse> =
        MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val errorResponseLiveData: MutableLiveData<String> = MutableLiveData()
}