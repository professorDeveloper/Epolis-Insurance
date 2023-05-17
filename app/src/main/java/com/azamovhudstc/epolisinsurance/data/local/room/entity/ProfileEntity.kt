package com.azamovhudstc.epolisinsurance.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val photoUri: String,
    val name: String,
    val lastName: String
) : Serializable