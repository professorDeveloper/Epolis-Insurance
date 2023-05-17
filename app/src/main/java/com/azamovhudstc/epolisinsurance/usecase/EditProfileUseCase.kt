package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface EditProfileUseCase {
    fun loadPhoneNumber(): Flow<Result<String>>
    fun updateProfile(profileEntity: ProfileEntity): Flow<Result<Unit>>

}