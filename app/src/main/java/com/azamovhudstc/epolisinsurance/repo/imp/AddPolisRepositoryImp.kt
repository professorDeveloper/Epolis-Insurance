package com.azamovhudstc.epolisinsurance.repo.imp

import com.azamovhudstc.epolisinsurance.repo.AddPolisRepository
import com.azamovhudstc.epolisinsurance.utils.LocalData.addSpinnerCat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class AddPolisRepositoryImp @Inject constructor(): AddPolisRepository {
    override fun loadAddSpinnerList(): Flow<ArrayList<String>> = flow{

        emit(addSpinnerCat())
    }.flowOn(Dispatchers.IO)
}