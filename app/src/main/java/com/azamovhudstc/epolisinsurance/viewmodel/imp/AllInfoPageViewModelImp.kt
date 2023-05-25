package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse
import com.azamovhudstc.epolisinsurance.usecase.TechUseCase
import com.azamovhudstc.epolisinsurance.viewmodel.AllInfoPageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AllInfoPageViewModelImp @Inject constructor(private val techUseCase: TechUseCase) :
    AllInfoPageViewModel, ViewModel() {
    override val responseLiveData: MutableLiveData<GetVehicleResponse> = MutableLiveData()
    override val errorResponseLiveData: MutableLiveData<String> = MutableLiveData()
    override val errorByIdResponseLiveData: MutableLiveData<String> = MutableLiveData()
    override val userDataResponseLiveData: MutableLiveData<GetUserDataByIdResponse> =
        MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override fun getUserData(request: PassportIdDataRequest) {
        progressLiveData.value = true
        techUseCase.getUserDataByID(request).onEach {
            it.onSuccess { value: GetUserDataByIdResponse ->
                userDataResponseLiveData.value = value
                progressLiveData.value = false
            }
            it.onFailure { exception: Throwable ->

                errorByIdResponseLiveData.value = exception.message
                progressLiveData.value = false


            }
        }.launchIn(viewModelScope)
    }

    override fun searchCar(request: GetVehicleRequest) {
        progressLiveData.value = true
        techUseCase.getTechDataByID(request).onEach { result ->
            result.onSuccess { value: GetVehicleResponse ->
                responseLiveData.value = value
                progressLiveData.value = false
            }
            result.onFailure { exception: Throwable ->
                errorResponseLiveData.value = exception.message
                progressLiveData.value = false
            }

        }.launchIn(viewModelScope)
    }
}