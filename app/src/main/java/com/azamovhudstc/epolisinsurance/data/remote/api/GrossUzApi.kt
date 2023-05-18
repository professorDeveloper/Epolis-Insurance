package com.azamovhudstc.epolisinsurance.data.remote.api

import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetTechPassResoponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GrossUzApi {

    @GET("/product/get-tech-pass-data")
    suspend fun getPassData(
        @Query("tech_series") tech_series: String,
        @Query("tech_number") tech_number: String,
        @Query("autonumber") autonumber: String
    ):Response<GetTechPassResoponse>
}