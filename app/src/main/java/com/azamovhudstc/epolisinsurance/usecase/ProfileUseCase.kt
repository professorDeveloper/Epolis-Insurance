package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import java.util.concurrent.Flow

interface ProfileUseCase {
    fun loadProfile():kotlinx.coroutines.flow.Flow<Result<String>>
    fun saveLanguage(languageType: LanguageType):kotlinx.coroutines.flow.Flow<Result<Unit>>
}