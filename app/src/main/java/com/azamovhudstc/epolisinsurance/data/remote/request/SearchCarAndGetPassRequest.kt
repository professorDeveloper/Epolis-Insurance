package com.azamovhudstc.epolisinsurance.data.remote.request

data class SearchCarAndGetPassRequest(
    val carNumber:String,
    val carTexSeries:String,
    val carTexSeriesNum:String,
)