package com.azamovhudstc.epolisinsurance.data.remote.response

data class GetUserDataByIdResponse(
    val error: Int,
    val error_message: String,
    val result: ResultX
)