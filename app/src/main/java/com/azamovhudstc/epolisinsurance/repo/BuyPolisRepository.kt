package com.azamovhudstc.epolisinsurance.repo

import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse
import kotlinx.coroutines.flow.Flow

interface BuyPolisRepository {
    fun  searchGetPassData(getVehicleRequest: GetVehicleRequest):Flow<Result<GetVehicleResponse>>
}