package com.azamovhudstc.epolisinsurance.data.model
data class Driver(
    val driverId:String,
    val driverPassSere:String?=null,
    val driverPassNumber:String?=null,
    val driverBorn:String?=null,
    val fio:String?=null,
    val kinship:String?=null,
    val driverLicenseSere:String?=null,
    val driverLicenseNumber:String?=null,
    val driverLicenseDateNumber:String?=null
)