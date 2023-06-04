package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.usecase.EditProfileUseCase
import com.azamovhudstc.epolisinsurance.viewmodel.EditProfileScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditProfileScreenViewModelImp @Inject constructor(private val useCase: EditProfileUseCase) :
    ViewModel(),
    EditProfileScreenViewModel {
    override val phoneLiveData: MutableLiveData<String> = MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val successEditLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val successProfileLiveData: MutableLiveData<ProfileEntity> = MutableLiveData()

    override fun loadPhone() {
        useCase.loadPhoneNumber().onEach {
            it.onSuccess {
                phoneLiveData.value = it
            }
        }
            .launchIn(viewModelScope)
    }

    override fun editProfile(profileEntity: ProfileEntity) {
        progressLiveData.value=true
        useCase.updateProfile(profileEntity).onEach { it ->
            it.onSuccess {
                delay(800)
                successEditLiveData.value = it
                progressLiveData.value=false
            }
            it.onFailure {
                delay(800)
                println(it.message)
                progressLiveData.value=false
            }
        }.launchIn(viewModelScope)
    }

    override fun initProfileData() {

        useCase.initProfileData().onEach {
            it.onSuccess {
                successProfileLiveData.value=it
            }
        }.launchIn(viewModelScope)
    }

}