package com.azamovhudstc.epolisinsurance.repo.imp

import android.util.Log
import com.azamovhudstc.epolisinsurance.data.remote.api.BuyPollsApi
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
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
    override fun searchGetPassData(getVehicleRequest: GetVehicleRequest): Flow<Result<GetVehicleResponse>> =
        flow {
            val passData = buyPolisApi.getTechVehicleData(
                getVehicleRequest.seria_field,
                getVehicleRequest.seria_number,
                getVehicleRequest.vehicle_number
            )

            if (passData.isSuccessful) {
                if (passData.body()?.error==0) {
                    emit(Result.success(passData.body()!!))
                } else {
                    println(passData?.body()?.error_message.toString())
                    emit(
                        Result.failure(Exception(passData!!.body()?.error_message))
                    )
                }
            } else {
                Log.d("!@#", "Exception: ${passData.errorBody()!!.string()}")
                emit(Result.failure(Exception(passData.errorBody()!!.string())))
            }

        }.catch {
            emit(Result.failure(it))
        }
            .flowOn(Dispatchers.IO)
}