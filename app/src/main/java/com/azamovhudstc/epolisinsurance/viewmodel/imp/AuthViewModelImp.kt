package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.data.remote.request.ConfirmRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.register.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.register.RegisterResponse
import com.azamovhudstc.epolisinsurance.usecase.AuthUseCase
import com.azamovhudstc.epolisinsurance.viewmodel.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModelImp @Inject constructor(private val authUseCase: AuthUseCase) : AuthViewModel,
    ViewModel() {
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val confirmResponseLiveData: MutableLiveData<ConfirmResponse> = MutableLiveData()
    override val registerResponseLiveData: MutableLiveData<RegisterResponse> = MutableLiveData()
    override val errorResponseLiveData: MutableLiveData<String> = MutableLiveData()
    override fun confirmOtp(confirmRequest: ConfirmRequest) {
        progressLiveData.value = true

        authUseCase.confirmOtp(confirmRequest).onEach { result ->
            result.onFailure { exception: Throwable ->
                errorResponseLiveData.value = exception.message
                delay(500)
                progressLiveData.value = false
            }
            result.onSuccess { confirm: ConfirmResponse ->
                confirmResponseLiveData.value = confirm
                delay(500)
                progressLiveData.value = false
            }
        }.launchIn(viewModelScope)
    }


    override fun registerUser(registerRequest: RegisterRequest) {
        progressLiveData.value = true
        authUseCase.registerUser(registerRequest).onEach {
            it.onSuccess { value: RegisterResponse ->
                registerResponseLiveData.value = value
                progressLiveData.value = false
            }
            it.onFailure { exception: Throwable ->
                errorResponseLiveData.value = exception.message
                progressLiveData.value = false
            }
        }.launchIn(viewModelScope)
    }
}