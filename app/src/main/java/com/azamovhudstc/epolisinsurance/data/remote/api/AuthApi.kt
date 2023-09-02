package com.azamovhudstc.epolisinsurance.data.remote.api

import com.azamovhudstc.epolisinsurance.data.remote.response.register.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("ru/api/accounts/register/")
    suspend fun registerUser(
        @Field("phone") phone: String,
    ): Response<RegisterResponse>

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("ru/api/accounts/confirm/")
    suspend fun confirmOtp(
        @Field("phone") phone: String,
        @Field("code") code: String
    ): Response<ConfirmResponse>



}