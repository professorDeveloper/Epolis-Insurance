package com.azamovhudstc.epolisinsurance.data.remote.response

data class ResultX(
    val address: String,
    val birthCountry: String,
    val birthDate: String,
    val birthPlace: String,
    val districtId: Int,
    val document: String,
    val endDate: String,
    val firstEng: String,
    val firstNameLatin: String,
    val gender: String,
    val issuedBy: String,
    val lastNameEng: String,
    val lastNameLatin: String,
    val middleNameLatin: String,
    val regionId: Int,
    val startDate: String
)