package com.azamovhudstc.epolisinsurance.data.remote.response.policy

data class Policy(
    val begin_date: String,
    val driver_count: Int,
    val driver_count_name: String,
    val end_date: String,
    val eosgo_uuid: Any,
    val id: Int,
    val num: String,
    val policy_begin_date: String,
    val premium: Int,
    val region: String
)