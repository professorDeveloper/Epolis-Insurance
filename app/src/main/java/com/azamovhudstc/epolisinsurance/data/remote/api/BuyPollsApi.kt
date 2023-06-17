package com.azamovhudstc.epolisinsurance.data.remote.api

import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.RemovedSuccessData
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.SubmitForm1Response
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse
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
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("ru/api/get_owner_data_by_pinfl/")
    suspend fun getUserDataByPassId(
        @Field("passportNumber") passportNumber: String,
        @Field("passportSeries") passportSeries: String,
        @Field("pinfl") pinfl: String,
        @Field("vehicle_id") vehicle_id: String,
    ): Response<GetUserDataByIdResponse>

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("ru/api/get_driver_passport_by_birth_date/")
    suspend fun getDriverData(
        @Field("birthDate") birthDate:String,
        @Field("passportSeries") driverPassportSeries:String,
        @Field("passportNumber") driverPassportNumber:String,
        @Field("vehicle_id") vehicleId:String,
        @Field("num") num:String,
        @Field("relationType")relationType:String,

    ):Response<DriverResponse>

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("ru/api/remove-driver/")
    suspend fun removeDriver(
        @Field("passportNumber") passportNumber: String,
        @Field("passportSeries")passportSeries:String,
        @Field("relationType") relationType:String,
        @Field("vehicle_id") vehicleId:String
    ):Response<RemovedSuccessData>

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("ru/api/submit-form1/")
    suspend fun submitForm1(
        @Field("phone") phone:String,
        @Field("begin_date") beginDate:String,
        @Field("policy_id")policyId:String,
        @Field("driver_count") driverCount:String,

    ):Response<SubmitForm1Response>

}