package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType

interface ProfileScreenViewModel {
    val successLanguageSaveData:MutableLiveData<Unit>
    val loadProfileData: MutableLiveData<String>
    val errorLoadProfileData: MutableLiveData<String>
    val successProfileData:MutableLiveData<ProfileEntity>
    val logoutSuccessLiveData:MutableLiveData<Unit>
    fun loadProfile()
    fun logout()
    fun saveLanguage(languageType: LanguageType)
}