package com.azamovhudstc.epolisinsurance.usecase.imp

import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRemoveRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.RemovedSuccessData
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import com.azamovhudstc.epolisinsurance.usecase.AddDriverUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddDriverUseCaseImp @Inject constructor(private val buyPolisRepository: BuyPolisRepository) :
    AddDriverUseCase {
    override fun removeDriver(driverRemoveRequest: DriverRemoveRequest): Flow<Result<RemovedSuccessData>> {
        return  buyPolisRepository.removeDriver(driverRemoveRequest)
    }

    override fun getDriverData(driverRequest: DriverRequest): Flow<Result<DriverResponse>> {
         return buyPolisRepository.getDriver(driverRequest)
    }
}