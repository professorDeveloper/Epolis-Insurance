package com.azamovhudstc.epolisinsurance.utils.wrapper

import com.azamovhudstc.epolisinsurance.data.remote.response.register.ConfirmResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.ErrorResponse

sealed class ResponseWrapper {
    data class Success(val data: ConfirmResponse) : ResponseWrapper()
    data class Error(val data: ErrorResponse) : ResponseWrapper()
    companion object {
        @JvmStatic
        fun success(data: ConfirmResponse): ResponseWrapper = Success(data)

        @JvmStatic
        fun error(data: ErrorResponse): ResponseWrapper = Error(data)
    }
}
