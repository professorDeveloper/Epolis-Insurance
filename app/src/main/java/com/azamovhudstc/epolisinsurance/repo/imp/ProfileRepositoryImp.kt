package com.azamovhudstc.epolisinsurance.repo.imp

import android.content.Context
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.repo.ProfileRepository
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor() : ProfileRepository {
    private val appReference = AppReference(context = App.instance)
    override fun loadProfile() = flow<Result<String>> {
        if (appReference.phone.isNotEmpty()) {
            emit(Result.success(appReference.phone))
        } else {
            emit(Result.failure(Exception("phone is Empty")))
        }
    }


    override fun saveLanguage(language: LanguageType) = flow<Result<Unit>> {
        appReference.currentLanguage = language.apply {
            emit(Result.success(Unit))
        }
    }
}