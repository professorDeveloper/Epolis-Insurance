package com.azamovhudstc.epolisinsurance.data.remote.response.driver

data class SubmitForm1Response(
    val all_drivers_fake: Boolean,
    val drivers: List<Driver>,
    val owner: Owner,
    val policy: Policy,
    val vehicle: Vehicle
)