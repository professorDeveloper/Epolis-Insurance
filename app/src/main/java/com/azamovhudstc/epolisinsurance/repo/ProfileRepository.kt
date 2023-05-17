package com.azamovhudstc.epolisinsurance.repo

import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import kotlinx.coroutines.flow.Flow


interface ProfileRepository {
    fun loadProfile(): Flow<Result<String>>
    fun saveLanguage(language: LanguageType): Flow<Result<Unit>>
}