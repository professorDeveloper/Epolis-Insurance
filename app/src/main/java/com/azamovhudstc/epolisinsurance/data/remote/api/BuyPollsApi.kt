package com.azamovhudstc.epolisinsurance.data.remote.api

import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface BuyPollsApi {

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("ru/api/get_vehicle_data/")
    suspend fun getTechVehicleData(
        @Field("seria_field") seria_field: String,
        @Field("seria_number") seria_number: String,
        @Field("vehicle_number") vehicle_number: String,
    ): Response<GetVehicleResponse>

}