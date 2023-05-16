package com.azamovhudstc.epolisinsurance.usecase.imp

import com.azamovhudstc.epolisinsurance.data.remote.request.ConfirmRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.RegisterResponse
import com.azamovhudstc.epolisinsurance.repo.AuthRepository
import com.azamovhudstc.epolisinsurance.usecase.AuthUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImp @Inject constructor(private val authRepository: AuthRepository):AuthUseCase {
    override fun registerUser(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> {

        return authRepository.registerUser(registerRequest)
    }

    override fun confirmOtp(confirmRequest: ConfirmRequest): Flow<Result<ConfirmResponse>> {
        return authRepository.otpConfirm(confirmRequest)
    }
}