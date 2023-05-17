package com.azamovhudstc.epolisinsurance.usecase.imp

import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.repo.imp.ProfileRepositoryImp
import com.azamovhudstc.epolisinsurance.usecase.ProfileUseCase
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCaseImp @Inject constructor(private val repo: ProfileRepositoryImp) :
    ProfileUseCase {
    override fun loadProfile(): Flow<Result<String>> {
        return repo.loadProfile()
    }

    override fun saveLanguage(languageType: LanguageType): Flow<Result<Unit>> {
        return repo.saveLanguage(languageType)
    }

    override fun initProfileWithImage(): Flow<Result<ProfileEntity>> {
        return repo.initProfileImage()
    }

    override fun logout(): Flow<Result<Unit>> {
        return repo.logout()
    }
}