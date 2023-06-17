package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.remote.request.ConfirmRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.register.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.register.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    fun registerUser(registerResponse: RegisterRequest): Flow<Result<RegisterResponse>>
    fun  confirmOtp(confirmRequest: ConfirmRequest):Flow<Result<ConfirmResponse>>
}