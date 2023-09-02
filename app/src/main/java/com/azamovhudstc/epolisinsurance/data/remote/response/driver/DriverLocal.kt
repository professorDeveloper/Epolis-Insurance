package com.azamovhudstc.epolisinsurance.data.remote.response.driver

data class DriverLocal(
    val licenseSeria: String?,
    val licenseNumber: String?,
    val licenseDate: String?,
    val firstNameLatin: String?,
    val birthDate: String?,
    val passportNumber: String?,
    val lastNameLatin: String?,
    val address: String?,
    val document: String?,
    val start_date: String?,
    val num: Int?,
    val passportSeries: String?,
    val middleNameLatin: String?,
    val fake: Boolean?
) {
    fun convertorDriverResponse(): DriverResponse = DriverResponse(
        address,
        null,
        birthDate,
        null,
        districtId = null,
        document = null,
        null,
        null,
        firstNameLatin,
        gender = null,
        null,
        null,
        lastNameLatin,
        licenseDate,
        licenseNumber,
        licenseSeria,
        middleNameLatin,
        "$passportSeries$passportNumber",
        null,
        start_date,
        num = num
    )
}