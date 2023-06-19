package com.azamovhudstc.epolisinsurance.data.remote.request

data class DriverRequest(
    val birthDate:String,
    val passportSeries:String,
    val passportNumber: String,
    val vehicleId:String,
    val driverNum:String,
    val kinshipType:String="2",

)