package com.azamovhudstc.epolisinsurance.data.remote.response

data class TechPassport(
    val bodyNumber: String,
    val comment: String,
    val division: String,
    val emptyWeight: String,
    val engineNumber: String,
    val fuelType: String,
    val fullWeight: String,
    val govNumber: String,
    val inspection: String,
    val issueYear: Int,
    val modelName: String,
    val owner: String,
    val pVehicleId: String,
    val pinfl: String,
    val seats: String,
    val stands: String,
    val techPassportIssueDate: String,
    val vehicleColor: String,
    val vehicleTypeId: Int
)