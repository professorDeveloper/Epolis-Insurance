package com.azamovhudstc.epolisinsurance.di

import com.azamovhudstc.epolisinsurance.repo.*
import com.azamovhudstc.epolisinsurance.repo.imp.*
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
    fun getSearchCarRepo(imp: BuyPolisRepositoryImp):BuyPolisRepository
    @Binds
    fun getAuthRepo(imp: AuthRepositoryImp): AuthRepository

    @Binds
    fun getProfileRepo(imp: ProfileRepositoryImp): ProfileRepository

    @Binds
    fun getProfileEditRepo(imp: EditProfileRepositoryImp): EditProfileRepository
}