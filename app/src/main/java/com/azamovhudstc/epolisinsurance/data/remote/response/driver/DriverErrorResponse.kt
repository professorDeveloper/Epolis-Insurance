package com.azamovhudstc.epolisinsurance.data.remote.response.driver

data class DriverErrorResponse(
    val error: Int,
    val error_message: String,
    val result: List<String>
)