package com.azamovhudstc.epolisinsurance.utils.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CustomConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {

        val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return CustomResponseConverter(delegate)
    }
}
