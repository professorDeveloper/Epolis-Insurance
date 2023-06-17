package com.azamovhudstc.epolisinsurance.utils

fun String.firstLetterUpper(): String {
    var firstLatter = substring(0, 1)
    var lastLetters = substring(1, length).lowercase()


    return "${firstLatter}$lastLetters"
}