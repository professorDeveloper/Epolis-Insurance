package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetTechPassResoponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TechUseCase {
    fun getTechDataByID(request: SearchCarAndGetPassRequest):Flow<Result<GetTechPassResoponse>>
}