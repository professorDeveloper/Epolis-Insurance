package com.azamovhudstc.epolisinsurance.repo.imp

import com.azamovhudstc.epolisinsurance.data.remote.api.GrossUzApi
import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetTechPassResoponse
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BuyPolisRepositoryImp @Inject constructor(private val grossUzApi: GrossUzApi) :
    BuyPolisRepository {
    override fun searchGetPassData(searchCarAndGetPassRequest: SearchCarAndGetPassRequest): Flow<Result<GetTechPassResoponse>> =
        flow {
            val passData = grossUzApi.getPassData(
                searchCarAndGetPassRequest.carTexSeries,
                searchCarAndGetPassRequest.carTexSeriesNum,
                searchCarAndGetPassRequest.carNumber
            )

            if (passData.isSuccessful) {
                emit(Result.success(passData.body()!!))
            }
            else{
                emit(Result.failure(Exception(passData.errorBody()!!.string())))
            }

        }.catch {
            emit(Result.failure(it))
        }
}