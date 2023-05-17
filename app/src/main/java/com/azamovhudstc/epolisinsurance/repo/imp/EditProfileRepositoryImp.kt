package com.azamovhudstc.epolisinsurance.repo.imp

import android.content.Context
import com.azamovhudstc.epolisinsurance.data.local.room.dao.ProfileDao
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.repo.EditProfileRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EditProfileRepositoryImp @Inject constructor(
    private val dao: ProfileDao,
    @ApplicationContext val context: Context
) :
    EditProfileRepository {
    override fun loadPhoneNumber() = flow {
        val appReference = AppReference(context)
        emit(Result.success(appReference.phone))
    }

    override fun updateProfile(profileEntity: ProfileEntity) = flow<Result<Unit>> {
        if (dao.getProfiles().isEmpty()) {
            dao.addProfileData(profileEntity)
            emit(Result.success(Unit))
        } else {
            dao.editProfileData(profileEntity)
            emit(Result.success(Unit))
        }
    }
}