package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.usecase.ProfileUseCase
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.viewmodel.ProfileScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModelImp @Inject constructor(private val useCase: ProfileUseCase) :
    ProfileScreenViewModel, ViewModel() {
    override val successLanguageSaveData: MutableLiveData<Unit> = MutableLiveData()
    override val loadProfileData: MutableLiveData<String> = MutableLiveData()
    override val errorLoadProfileData: MutableLiveData<String> = MutableLiveData()

    override fun loadProfile() {
        useCase.loadProfile().onEach {
            it.onSuccess {
                loadProfileData.value = it
            }
            it.onFailure {
                errorLoadProfileData.value = it.message
            }

        }.launchIn(viewModelScope)
    }

    override fun saveLanguage(languageType: LanguageType) {
        useCase.saveLanguage(languageType).onEach {
            it.onSuccess {
                successLanguageSaveData.value = it
            }
        }.launchIn(viewModelScope)
    }
}