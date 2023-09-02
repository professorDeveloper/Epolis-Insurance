package com.azamovhudstc.epolisinsurance.repo.imp

import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.remote.api.AuthApi
import com.azamovhudstc.epolisinsurance.data.remote.request.ConfirmRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.register.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.register.RegisterResponse
import com.azamovhudstc.epolisinsurance.repo.AuthRepository
import com.azamovhudstc.epolisinsurance.tools.hasConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override fun otpConfirm(otpRequest: ConfirmRequest): Flow<Result<ConfirmResponse>> = flow {
        val confirmOtp = authApi.confirmOtp(otpRequest.phone, otpRequest.code)
        if (hasConnection()) {
            if (confirmOtp.isSuccessful) {
                val response=confirmOtp.body()!!
                if (response.error!=null){
                    emit(Result.failure(Exception(response.error.message)))
                }
                else{
                    emit(Result.success(response))
                }
            }
        } else {
            emit(Result.failure(java.lang.Exception(App.instance.getString(R.string.error_))))
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)

    override fun registerUser(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> =
        flow {
            val registerUser = authApi.registerUser(registerRequest.phone)
            if (registerUser.isSuccessful) {
                emit(Result.success(registerUser.body()!!))
            } else {
                emit(Result.failure(Exception(App.instance.getString(R.string.error_))))
            }

        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
}