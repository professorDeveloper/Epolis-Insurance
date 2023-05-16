package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.repo.AddPolisRepository
import com.azamovhudstc.epolisinsurance.repo.imp.AddPolisRepositoryImp
import com.azamovhudstc.epolisinsurance.viewmodel.AddPolisViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class AddPolisViewModelImp @Inject constructor(private val addPolisRepository: AddPolisRepository): AddPolisViewModel,ViewModel() {
    override val addSpinnerLiveData: MutableLiveData<ArrayList<String>> =MutableLiveData()

    override fun getSpinnerData() {
        addPolisRepository.loadAddSpinnerList().onEach {
            addSpinnerLiveData.value=it
        }.launchIn(viewModelScope)
    }
}