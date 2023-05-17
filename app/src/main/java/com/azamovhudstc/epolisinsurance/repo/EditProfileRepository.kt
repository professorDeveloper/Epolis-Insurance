package com.azamovhudstc.epolisinsurance.repo

import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface EditProfileRepository {
    fun loadPhoneNumber():Flow<Result<String>>
    fun updateProfile(profileEntity: ProfileEntity):Flow<Result<Unit>>
    fun initProfile():Flow<Result<ProfileEntity>>

}