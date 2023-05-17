package com.azamovhudstc.epolisinsurance.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.epolisinsurance.data.local.room.dao.ProfileDao
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity

interface EditProfileScreenViewModel {
    val phoneLiveData :MutableLiveData<String>
    val successEditLiveData:MutableLiveData<Unit>
    val successProfileLiveData:MutableLiveData<ProfileEntity>
    fun loadPhone()
    fun editProfile(profileEntity: ProfileEntity)
    fun initProfileData()

}