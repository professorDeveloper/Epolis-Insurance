package com.azamovhudstc.epolisinsurance.repo.imp

import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.local.room.dao.ProfileDao
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.repo.ProfileRepository
import com.azamovhudstc.epolisinsurance.utils.enums.CurrentScreenEnum
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(private val dao: ProfileDao) : ProfileRepository {
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

    override fun initProfileImage() = flow<Result<ProfileEntity>> {
        if (dao.getProfiles().isNotEmpty()) {
            emit(Result.success(dao.getProfileDataById(1)!!))
        } else {
            emit(Result.failure(Exception("Malumot Hali qo`shilmagan ")))
        }
    }

    override fun logout() = flow<Result<Unit>> {
        dao.clear()
        appReference.phone = ""
        appReference.token = ""
        emit(Result.success(Unit))

    }
}