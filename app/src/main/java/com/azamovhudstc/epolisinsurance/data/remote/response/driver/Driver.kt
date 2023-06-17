package com.azamovhudstc.epolisinsurance.data.remote.response.driver

data class Driver(
    val birth_date: String,
    val document: String,
    val fake: Boolean,
    val first_name_latin: String,
    val last_name_latin: String,
    val license_date: String,
    val license_number: String,
    val license_seria: String,
    val middle_name_latin: String,
    val num: Int,
    val passport_number: String,
    val passport_series: String,
    val pinfl: String,
    val relation_type: Int,
    val relation_type_disabled: String,
    val relation_type_name: String,
    val start_date: String
)