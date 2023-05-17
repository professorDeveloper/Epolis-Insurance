package com.azamovhudstc.epolisinsurance.utils

import java.util.regex.Pattern

fun String.checkPhone():Boolean{
    val REG ="^(\\+998|998)(90|91|93|94|95|97|98|99|88)\\d{7}$"
    val pattern = Pattern.compile(REG)
    return pattern.matcher(this).find()
}