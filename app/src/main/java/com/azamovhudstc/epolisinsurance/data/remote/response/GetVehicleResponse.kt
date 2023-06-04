package com.azamovhudstc.epolisinsurance.data.remote.response

data class GetVehicleResponse(
    val error: Int,
    val error_message: String,
    val result: Result
)