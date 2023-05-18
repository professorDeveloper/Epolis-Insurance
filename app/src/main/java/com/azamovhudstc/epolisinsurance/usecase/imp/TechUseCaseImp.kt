package com.azamovhudstc.epolisinsurance.usecase.imp

import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetTechPassResoponse
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import com.azamovhudstc.epolisinsurance.usecase.TechUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class TechUseCaseImp @Inject constructor(private val buyPolisRepository: BuyPolisRepository) :
    TechUseCase {
    override fun getTechDataByID(request: SearchCarAndGetPassRequest): Flow<Result<GetTechPassResoponse>> {
        return buyPolisRepository.searchGetPassData(request)
    }
}