package com.azamovhudstc.epolisinsurance.data.remote.response.driver

data class DriverResponse(
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
    val licenseDate: String,
    val licenseNumber: String,
    val licenseSeria: String,
    val middleNameLatin: String,
    val pinfl: String,
    val regionId: Int,
    val startDate: String
) {
    override fun toString(): String {
        return "DriverResponse(address='$address', birthCountry='$birthCountry', birthDate='$birthDate', birthPlace='$birthPlace', districtId=$districtId, document='$document', endDate='$endDate', firstEng='$firstEng', firstNameLatin='$firstNameLatin', gender='$gender', issuedBy='$issuedBy', lastNameEng='$lastNameEng', lastNameLatin='$lastNameLatin', licenseDate='$licenseDate', licenseNumber='$licenseNumber', licenseSeria='$licenseSeria', middleNameLatin='$middleNameLatin', pinfl='$pinfl', regionId=$regionId, startDate='$startDate')"
    }
}