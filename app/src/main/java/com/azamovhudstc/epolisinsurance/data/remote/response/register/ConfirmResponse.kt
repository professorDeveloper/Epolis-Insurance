package com.azamovhudstc.epolisinsurance.data.remote.response.register

data class ConfirmResponse(
    var `data`: DataX,
    val error: ErrorOtp
)