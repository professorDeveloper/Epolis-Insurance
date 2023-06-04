package com.azamovhudstc.epolisinsurance.usecase.imp

import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import com.azamovhudstc.epolisinsurance.usecase.TechUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TechUseCaseImp @Inject constructor(private val buyPolisRepository: BuyPolisRepository) :
    TechUseCase {
    override fun getUserDataByID(request: PassportIdDataRequest): Flow<Result<GetUserDataByIdResponse>> {
        return          buyPolisRepository.getUserDataByPassportSeries(request)
    }

    override fun getTechDataByID(request: GetVehicleRequest): Flow<Result<GetVehicleResponse>> {
        return buyPolisRepository.searchGetPassData(request)
    }
}