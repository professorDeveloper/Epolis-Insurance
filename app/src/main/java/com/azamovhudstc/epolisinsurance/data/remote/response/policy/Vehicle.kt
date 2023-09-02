package com.azamovhudstc.epolisinsurance.data.remote.response.policy

data class Vehicle(
    val body_number: String,
    val engine_number: String,
    val gov_number: String,
    val id: Int,
    val issue_year: Int,
    val license_number: String,
    val license_seria: String,
    val model_name: String,
    val p_vehicle_id: String,
    val tech_passport_issue_date: String,
    val vehicle_type_id: Int,
    val vehicle_type_name: String
)