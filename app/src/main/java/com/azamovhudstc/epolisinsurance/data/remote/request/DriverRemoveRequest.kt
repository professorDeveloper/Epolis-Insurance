package com.azamovhudstc.epolisinsurance.data.remote.request

data class DriverRemoveRequest(
    val removeDriverPassportNumber:String,
    val removeDriverPassportSeries:String,
    val vehicleID:String,
    val removeDriverPosition:String,
){
    override fun toString(): String {
        return "DriverRemoveRequest(removeDriverPassportNumber='$removeDriverPassportNumber', removeDriverPassportSeries='$removeDriverPassportSeries', vehicleID='$vehicleID', removeDriverPosition='$removeDriverPosition')"
    }
}