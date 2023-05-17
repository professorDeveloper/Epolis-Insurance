package com.azamovhudstc.epolisinsurance.usecase.imp

import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.repo.imp.EditProfileRepositoryImp
import com.azamovhudstc.epolisinsurance.usecase.EditProfileUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditProfileUseCaseImp @Inject constructor(private val repo: EditProfileRepositoryImp) :
    EditProfileUseCase {
    override fun loadPhoneNumber(): Flow<Result<String>> {
        return repo.loadPhoneNumber()
    }

    override fun updateProfile(profileEntity: ProfileEntity): Flow<Result<Unit>> {
        return repo.updateProfile(profileEntity)
    }
}