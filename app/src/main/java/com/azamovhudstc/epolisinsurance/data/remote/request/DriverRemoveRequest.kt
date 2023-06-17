package com.azamovhudstc.epolisinsurance.data.remote.request

data class DriverRemoveRequest(
    val removeDriverPassportNumber:String,
    val removeDriverPassportSeries:String,
    val vehicleID:String,
    val removeDriverRelationType:String,
)