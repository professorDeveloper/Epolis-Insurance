package com.azamovhudstc.epolisinsurance.usecase.imp

import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitPolicyRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.policy.SubmitPolicyResponse
import com.azamovhudstc.epolisinsurance.repo.BuyPolisRepository
import com.azamovhudstc.epolisinsurance.usecase.ContactScreenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactScreenUseCaseImp @Inject constructor(private val buyPolisRepository: BuyPolisRepository)  : ContactScreenUseCase {
    override fun submitPolicy(submitPolicyRequest: SubmitPolicyRequest): Flow<Result<SubmitPolicyResponse>> {
        return  buyPolisRepository.submitPolicy(submitPolicyRequest)
    }
}