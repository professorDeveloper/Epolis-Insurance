package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {
    fun loadProfile(): kotlinx.coroutines.flow.Flow<Result<String>>
    fun saveLanguage(languageType: LanguageType): kotlinx.coroutines.flow.Flow<Result<Unit>>
    fun initProfileWithImage(): kotlinx.coroutines.flow.Flow<Result<ProfileEntity>>
    fun logout():Flow<Result<Unit>>
}