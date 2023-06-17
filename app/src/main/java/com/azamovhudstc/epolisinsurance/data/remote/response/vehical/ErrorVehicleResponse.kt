package com.azamovhudstc.epolisinsurance.data.remote.response.vehical

data class ErrorVehicleResponse(
    val error: Int,
    val error_message: String,
    val result: List<String>
)