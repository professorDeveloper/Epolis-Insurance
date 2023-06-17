package com.azamovhudstc.epolisinsurance.data.remote.request

data class SubmitForm1Request(
    val phone:String,
    val beginDate:String,
    val policyId:String,
    val driverCount:String
)