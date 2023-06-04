package com.azamovhudstc.epolisinsurance.data.remote.response

data class ErrorVehicleResponse(
    val error: Int,
    val error_message: String,
    val result: List<String>
)