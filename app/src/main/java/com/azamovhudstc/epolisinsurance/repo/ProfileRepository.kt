package com.azamovhudstc.epolisinsurance.repo

import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import kotlinx.coroutines.flow.Flow


interface ProfileRepository {
    fun loadProfile(): Flow<Result<String>>
    fun saveLanguage(language: LanguageType): Flow<Result<Unit>>
    fun initProfileImage():Flow<Result<ProfileEntity>>
    fun logout():Flow<Result<Unit>>
}