package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRemoveRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.DriverRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.RemovedSuccessData
import kotlinx.coroutines.flow.Flow

interface AddDriverUseCase {
    fun removeDriver(driverRemoveRequest: DriverRemoveRequest):Flow<Result<RemovedSuccessData>>
    fun getDriverData(driverRequest: DriverRequest):Flow<Result<DriverResponse>>
}