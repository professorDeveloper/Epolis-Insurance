package com.azamovhudstc.epolisinsurance.data.remote.response

data class GetTechPassResoponse(
    val address: String,
    val firstname: String,
    val lastname: String,
    val middlename: String,
    val name: String,
    val passport_number: String,
    val passport_series: String,
    val pinfl: String,
    val techPassport: TechPassport
)