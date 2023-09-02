package com.azamovhudstc.epolisinsurance.data.model

data class UserDetail(
    val id: Long,
    var requestId: String,
    var paymentId: Long?,
    var paid: Boolean
)