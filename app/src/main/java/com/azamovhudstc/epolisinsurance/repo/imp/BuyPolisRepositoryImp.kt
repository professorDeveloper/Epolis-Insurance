package com.azamovhudstc.epolisinsurance.repo.imp

import android.annotation.SuppressLint
import android.util.Log
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.remote.api.BuyPollsApi
import com.azamovhudstc.epolisinsurance.data.remote.request.*
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverErrorResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.DriverResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.driver.SubmitForm1Response
import com.azamovhudstc.epolisinsurance.data.remote.response.policy.SubmitPolicyResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetUserDataByIdResponse
import com.azamovhudstc.epolisinsurance.data.remote.response.vehical.GetVehicleResponse
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BuyPolisRepositoryImp @Inject constructor(private val buyPolisApi: BuyPollsApi) :
    BuyPolisRepository {
    override fun submitForm1(submitRequest: SubmitRequest): Flow<Result<SubmitForm1Response>> =
        flow {
            val submitForm1 = buyPolisApi.submitForm1(
                submitRequest.phone,
                submitRequest.beginDate,
                submitRequest.policyId,
                submitRequest.driverCount
            )

            if (submitForm1.isSuccessful) {
                emit(Result.success(submitForm1.body()!!))
            } else {
                emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))

            }

    @SuppressLint("ResourceType")
    override fun removeDriver(driverRemoveRequest: DriverRemoveRequest) = flow {


        val removeDriver = buyPolisApi.removeDriver(
            driverRemoveRequest.removeDriverPassportNumber,
            driverRemoveRequest.removeDriverPassportSeries,
            driverRemoveRequest.removeDriverPosition,
            driverRemoveRequest.vehicleID,

            )
        if (
            removeDriver.isSuccessful
        ) {
            emit(Result.success(removeDriver.body()!!))
        } else {
            emit(
                Result.failure(
                    Exception(
                        App.instance.resources.getString(R.string.error_api).toString()
                    )
                )
            )
        }
    }.catch {
        emit(Result.failure(Exception(it.message.toString())))
    }
        .flowOn(Dispatchers.IO)

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

    override fun searchGetPassData(getVehicleRequest: GetVehicleRequest): Flow<Result<GetVehicleResponse>> =
        flow {
            val passData = buyPolisApi.getTechVehicleData(
                getVehicleRequest.seria_field,
                getVehicleRequest.seria_number,
                getVehicleRequest.vehicle_number
            )

            if (passData.isSuccessful) {
                if (passData.body()?.error == 0) {
                    emit(Result.success(passData.body()!!))
                } else {
                    println("asdasdadsasd" + passData?.body()?.error_message.toString())
                    emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))
                }
            } else {
                Log.d("!@#", "Exception: ${passData.errorBody()!!.string()}")
                emit(Result.failure(Exception(passData.errorBody()!!.string())))
            }

        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)

    override fun getDriver(driverRequest: DriverRequest): Flow<Result<DriverResponse>> = flow {
        val driverData = buyPolisApi.getDriverData(
            driverRequest.birthDate,
            driverRequest.passportSeries,
            driverRequest.passportNumber,
            num = driverRequest.driverNum,
            vehicleId = driverRequest.vehicleId,
            relationType = driverRequest.kinshipType,
        )

        if (driverData.isSuccessful) {
            if (driverData.body().toString() != "null" || driverData.errorBody() == null) {
                println(":Errro ${driverData.errorBody()?.string()}")
                println(":Errro ${driverData.body()?.toString()}")

                if (driverData.body()!!.address == null) {
                    emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))

                } else {
                    emit(Result.success(driverData.body()!!))

                }

            } else {
                var gson = Gson()
                println(":Errro ${driverData.errorBody()?.string()}")

                val driverErrorResponse = gson.fromJson<DriverErrorResponse>(
                    driverData.body().toString(),
                    DriverErrorResponse::class.java
                )

                var error = "${driverErrorResponse.error_message} ${driverErrorResponse.result[0]}"
                emit(Result.failure(Exception(error)))
            }
        } else {
            emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))
        }
    }
        .catch {
            emit(Result.failure(it))
        }
        .flowOn(Dispatchers.IO)


    override fun submitPolicy(submitRequest: SubmitPolicyRequest): Flow<Result<SubmitPolicyResponse>> =
        flow {
            val submitPolicy = buyPolisApi.submitPolicy(
                submitRequest.phone,
                submitRequest.beginDate,
                submitRequest.policyId
            )
            if (submitPolicy.isSuccessful) {
                emit(Result.success(submitPolicy.body()!!))
            } else {
                emit(Result.failure(Exception(App.instance.resources.getString(R.string.error_api))))
            }

        }.catch {
            emit(Result.failure(it))

        }

}