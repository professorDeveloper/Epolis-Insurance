package com.azamovhudstc.epolisinsurance.di

import com.azamovhudstc.epolisinsurance.usecase.AuthUseCase
import com.azamovhudstc.epolisinsurance.usecase.EditProfileUseCase
import com.azamovhudstc.epolisinsurance.usecase.ProfileUseCase
import com.azamovhudstc.epolisinsurance.usecase.TechUseCase
import com.azamovhudstc.epolisinsurance.usecase.imp.AuthUseCaseImp
import com.azamovhudstc.epolisinsurance.usecase.imp.EditProfileUseCaseImp
import com.azamovhudstc.epolisinsurance.usecase.imp.ProfileUseCaseImp
import com.azamovhudstc.epolisinsurance.usecase.imp.TechUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun getAuthUseCase(imp:AuthUseCaseImp):AuthUseCase


    @Binds
    fun getProfileUseCase(imp:ProfileUseCaseImp):ProfileUseCase

    @Binds
    fun getEditProfileUseCase(imp:EditProfileUseCaseImp):EditProfileUseCase

    @Binds
    fun getTEchUseCase(techUseCaseImp: TechUseCaseImp):TechUseCase

}