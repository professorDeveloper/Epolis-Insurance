package com.azamovhudstc.epolisinsurance.repo.imp

import android.util.Log
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.remote.api.BuyPollsApi
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.data.remote.request.PassportIdDataRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.GetVehicleResponse
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import com.azamovhudstc.epolisinsurance.utils.converter.errorVehicleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BuyPolisRepositoryImp @Inject constructor(private val buyPolisApi: BuyPollsApi) :
    BuyPolisRepository {
    override fun getUserDataByPassportSeries(getUserDataByPassportIdDataRequest: PassportIdDataRequest): Flow<Result<GetUserDataByIdResponse>> =
        flow {

            val response = buyPolisApi.getUserDataByPassId(
                getUserDataByPassportIdDataRequest.passportNumber,
                getUserDataByPassportIdDataRequest.passportSeries,
                getUserDataByPassportIdDataRequest.pinfl,
                getUserDataByPassportIdDataRequest.vehicle_id
            )

            if (response.isSuccessful) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))
            }

        }.catch {
            emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))
        }.flowOn(Dispatchers.IO)


    override fun searchGetPassData(getVehicleRequest: GetVehicleRequest): Flow<Result<GetVehicleResponse>> = flow {
            val passData = buyPolisApi.getTechVehicleData(
                getVehicleRequest.seria_field,
                getVehicleRequest.seria_number,
                getVehicleRequest.vehicle_number
            )

            if (passData.isSuccessful) {
                if (passData.body()?.error == 0) {
                    emit(Result.success(passData.body()!!))
                } else {
                    println(passData?.body()?.error_message.toString())
                    emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))
                }
            } else {
                Log.d("!@#", "Exception: ${passData.errorBody()!!.string()}")
                emit(Result.failure(Exception(passData.errorBody()!!.string())))
            }

        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)

}