package com.azamovhudstc.epolisinsurance.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azamovhudstc.epolisinsurance.data.local.room.dao.ProfileDao
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

}