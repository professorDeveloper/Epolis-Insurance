package com.azamovhudstc.epolisinsurance.data.remote.api

import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("/accounts/register")
    fun registerUser(
        @Field("phone") phone: String,
        @Field("name") name: String
    ): Response<RegisterResponse>

    @FormUrlEncoded
    @POST("/accounts/confirm")
    fun confirmOtp(
        @Field("phone") phone: String,
        @Field("code") code: String
    ): Response<ConfirmResponse>


}