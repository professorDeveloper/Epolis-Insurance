package com.azamovhudstc.epolisinsurance.data.remote.response.policy

data class Owner(
    val birth_date: String,
    val fio: String,
    val is_yurik: Boolean,
    val passport: String,
    val passport_number: String,
    val passport_seria: String,
    val phone: String,
    val pinfl: String,
    val semi_phone: String
)