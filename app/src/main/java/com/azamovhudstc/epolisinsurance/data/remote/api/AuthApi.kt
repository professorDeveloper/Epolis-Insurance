package com.azamovhudstc.epolisinsurance.data.remote.api

import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.RegisterResponse
import com.azamovhudstc.epolisinsurance.utils.wrapper.ResponseWrapper
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

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