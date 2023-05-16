package com.azamovhudstc.epolisinsurance.di

import com.azamovhudstc.epolisinsurance.usecase.AuthUseCase
import com.azamovhudstc.epolisinsurance.usecase.imp.AuthUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun getAuthUseCase(imp:AuthUseCaseImp):AuthUseCase

}