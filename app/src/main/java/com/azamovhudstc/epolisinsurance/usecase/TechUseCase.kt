package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse
import kotlinx.coroutines.flow.Flow

interface TechUseCase {
    fun getUserDataByID(request: PassportIdDataRequest):Flow<Result<GetUserDataByIdResponse>>
    fun getTechDataByID(request: GetVehicleRequest):Flow<Result<GetVehicleResponse>>
}