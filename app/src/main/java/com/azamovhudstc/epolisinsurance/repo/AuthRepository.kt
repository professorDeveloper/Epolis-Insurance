package com.azamovhudstc.epolisinsurance.repo

import com.azamovhudstc.epolisinsurance.data.remote.request.ConfirmRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.register.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.register.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository{
    fun otpConfirm(otpRequest: ConfirmRequest):Flow<Result<ConfirmResponse>>
    fun registerUser(registerRequest: RegisterRequest):Flow<Result<RegisterResponse>>
}