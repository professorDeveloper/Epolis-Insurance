package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType

interface ProfileScreenViewModel {
    val successLanguageSaveData:MutableLiveData<Unit>
    val loadProfileData: MutableLiveData<String>
    val errorLoadProfileData: MutableLiveData<String>
    fun loadProfile()
    fun saveLanguage(languageType: LanguageType)
}