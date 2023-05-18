package com.azamovhudstc.epolisinsurance.repo

import com.azamovhudstc.epolisinsurance.data.remote.api.GrossUzApi
import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetTechPassResoponse
import kotlinx.coroutines.flow.Flow

interface BuyPolisRepository {
    fun  searchGetPassData(searchCarAndGetPassRequest: SearchCarAndGetPassRequest):Flow<Result<GetTechPassResoponse>>
}