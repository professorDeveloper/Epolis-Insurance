package com.azamovhudstc.epolisinsurance.utils

fun <T> T.within(action: T.() -> Unit) {
    this.action()
}