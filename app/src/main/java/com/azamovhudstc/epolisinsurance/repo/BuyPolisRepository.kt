package com.azamovhudstc.epolisinsurance.repo

import com.azamovhudstc.epolisinsurance.data.remote.request.*
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.RemovedSuccessData
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.SubmitForm1Response
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse
import kotlinx.coroutines.flow.Flow

interface BuyPolisRepository {
    fun submitForm1(submitRequest: SubmitRequest):Flow<Result<SubmitForm1Response>>
    fun removeDriver(driverRemoveRequest: DriverRemoveRequest):Flow<Result<RemovedSuccessData>>
    fun getUserDataByPassportSeries(getUserDataByPassportIdDataRequest: PassportIdDataRequest):Flow<Result<GetUserDataByIdResponse>>
    fun  searchGetPassData(getVehicleRequest: GetVehicleRequest):Flow<Result<GetVehicleResponse>>
    fun getDriver(driverRequest: DriverRequest):Flow<Result<DriverResponse>>
    fun submitCar(submitRequest: SubmitRequest):Flow<Result<Boolean>>
}