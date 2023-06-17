package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.SubmitForm1Response
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse
import kotlinx.coroutines.flow.Flow

interface AllInfoScreenUseCase {
    fun submitForm1(submitRequest: SubmitRequest):Flow<Result<SubmitForm1Response>>
    fun getUserDataByID(request: PassportIdDataRequest):Flow<Result<GetUserDataByIdResponse>>
    fun getTechDataByID(request: GetVehicleRequest):Flow<Result<GetVehicleResponse>>
}