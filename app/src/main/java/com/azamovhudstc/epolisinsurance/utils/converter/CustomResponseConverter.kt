package com.azamovhudstc.epolisinsurance.utils.converter

import com.azamovhudstc.epolisinsurance.data.remote.response.ErrorVehicleResponse
import com.azamovhudstc.epolisinsurance.repo.imp.BuyPolisRepositoryImp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter

class CustomResponseConverter<T>(private val delegate: Converter<ResponseBody, T>) :
    Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T? {
        val responseString = value.string()
        if (responseString.contains("message") && responseString.contains("num")) {
            return delegate.convert(ResponseBody.create(value.contentType(), responseString))
        }
        if (responseString.contains("token") && responseString.contains("error")) {
            return delegate.convert(ResponseBody.create(value.contentType(), responseString))
        }
        return null
    }
}
fun BuyPolisRepositoryImp.errorVehicleResponse(json: String): ErrorVehicleResponse {
    var gson = Gson()
    var type = object : TypeToken<ErrorVehicleResponse>() {}.type
    val fromJson = gson.fromJson<ErrorVehicleResponse>(json, type)
    return fromJson
}
