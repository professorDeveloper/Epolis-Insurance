package com.azamovhudstc.epolisinsurance.repo.imp

import android.util.Log
import com.azamovhudstc.epolisinsurance.data.remote.api.GrossUzApi
import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.GetTechPassResoponse
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
                if (passData.body()?.address!=null && passData?.body()?.techPassport!=null){
                    Log.d("!@#", "searchGetPassData: ${passData.body()!!.toString()}")
                    emit(Result.success(passData.body()!!))
                }
                else{
                    emit(Result.failure(Exception(passData.errorBody()!!.string())))
                }
            }
            else{
                Log.d("!@#", "Exception: ${passData.errorBody()!!.string()}")
                emit(Result.failure(Exception(passData.errorBody()!!.string())))
            }

        }.catch {
            emit(Result.failure(it))
        }
            .flowOn(Dispatchers.IO)
}