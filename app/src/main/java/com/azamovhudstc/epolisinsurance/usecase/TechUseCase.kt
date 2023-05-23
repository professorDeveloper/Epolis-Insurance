package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse
import kotlinx.coroutines.flow.Flow

interface TechUseCase {
    fun getTechDataByID(request: GetVehicleRequest):Flow<Result<GetVehicleResponse>>
}