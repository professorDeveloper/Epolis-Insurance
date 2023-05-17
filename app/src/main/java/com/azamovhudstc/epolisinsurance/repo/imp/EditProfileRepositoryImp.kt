package com.azamovhudstc.epolisinsurance.repo.imp

import com.azamovhudstc.epolisinsurance.data.local.room.dao.ProfileDao
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.repo.EditProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EditProfileRepositoryImp @Inject constructor(private val dao: ProfileDao) :
    EditProfileRepository {
    override fun loadPhoneNumber()=flow<Result<String>> {

    }

    override fun updateProfile(profileEntity: ProfileEntity)=flow<Result<Unit>> {
        if (dao.getProfileDataById(1)!=null){


        }
    }
}