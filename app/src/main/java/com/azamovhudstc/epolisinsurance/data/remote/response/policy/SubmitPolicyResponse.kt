package com.azamovhudstc.epolisinsurance.data.remote.response.policy

data class SubmitPolicyResponse(
    val all_drivers_fake: Boolean,
    val available: Boolean,
    val drivers: List<Driver>,
    val owner: Owner,
    val policy: Policy,
    val vehicle: Vehicle
)