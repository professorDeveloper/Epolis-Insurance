package com.azamovhudstc.epolisinsurance.data.remote.response.policy

data class Driver(
    val fake: Boolean,
    val num: Int,
    val relation_type: Int,
    val relation_type_disabled: String,
    val show: String
)