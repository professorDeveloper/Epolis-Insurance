package com.azamovhudstc.epolisinsurance.di

import com.azamovhudstc.epolisinsurance.repo.AddPolisRepository
import com.azamovhudstc.epolisinsurance.repo.AuthRepository
import com.azamovhudstc.epolisinsurance.repo.EditProfileRepository
import com.azamovhudstc.epolisinsurance.repo.ProfileRepository
import com.azamovhudstc.epolisinsurance.repo.imp.AddPolisRepositoryImp
import com.azamovhudstc.epolisinsurance.repo.imp.AuthRepositoryImp
import com.azamovhudstc.epolisinsurance.repo.imp.EditProfileRepositoryImp
import com.azamovhudstc.epolisinsurance.repo.imp.ProfileRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun getAddPolisRepo(imp: AddPolisRepositoryImp): AddPolisRepository

    @Binds
    fun getAuthRepo(imp: AuthRepositoryImp): AuthRepository

    @Binds
    fun getProfileRepo(imp: ProfileRepositoryImp): ProfileRepository

    @Binds
    fun getProfileEditRepo(imp: EditProfileRepositoryImp): EditProfileRepository
}