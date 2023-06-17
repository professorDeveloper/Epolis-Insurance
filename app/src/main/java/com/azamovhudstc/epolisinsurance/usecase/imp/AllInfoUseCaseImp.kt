package com.azamovhudstc.epolisinsurance.usecase.imp

import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.SubmitForm1Response
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import com.azamovhudstc.epolisinsurance.usecase.AllInfoScreenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllInfoUseCaseImp @Inject constructor(private val buyPolisRepository: BuyPolisRepository) :
    AllInfoScreenUseCase {
    override fun submitForm1(submitRequest: SubmitRequest): Flow<Result<SubmitForm1Response>> {
        return buyPolisRepository.submitForm1(submitRequest)
    }

    override fun getUserDataByID(request: PassportIdDataRequest): Flow<Result<GetUserDataByIdResponse>> {
        return          buyPolisRepository.getUserDataByPassportSeries(request)
    }

    override fun getTechDataByID(request: GetVehicleRequest): Flow<Result<GetVehicleResponse>> {
        return buyPolisRepository.searchGetPassData(request)
    }
}