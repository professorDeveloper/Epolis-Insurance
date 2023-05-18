package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetTechPassResoponse
import com.azamovhudstc.epolisinsurance.usecase.TechUseCase
import com.azamovhudstc.epolisinsurance.viewmodel.BuyPolisScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BuyPolisScreenViewModelImp @Inject constructor(private val techUseCase: TechUseCase) :
    BuyPolisScreenViewModel, ViewModel() {
    override val responseLiveData: MutableLiveData<GetTechPassResoponse> = MutableLiveData()
    override val errorResponseLiveData: MutableLiveData<String> = MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override fun searchCar(request: SearchCarAndGetPassRequest) {
        progressLiveData.value=true
        techUseCase.getTechDataByID(request).onEach { result ->
            result.onSuccess { value: GetTechPassResoponse ->
                responseLiveData.value = value
                progressLiveData.value=false
            }
            result.onFailure { exception: Throwable ->
                delay(600)
                errorResponseLiveData.value=exception.message
                progressLiveData.value=false
            }

        }.launchIn(viewModelScope)
    }
}