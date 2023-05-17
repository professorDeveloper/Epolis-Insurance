package com.azamovhudstc.epolisinsurance.data.local.room.dao

import androidx.room.*
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity

@Dao
interface ProfileDao {
    @Query("SELECT * from profileentity where id=:id")
    suspend fun getProfileDataById(id: Int): ProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfileData(profileEntity: ProfileEntity)

    @Update
    suspend fun editProfileData(profileEntity: ProfileEntity)

//    @Query("SELECT * from profileEntity ")
//    suspend fun getProfile
}