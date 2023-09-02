package com.azamovhudstc.epolisinsurance.usecase

import com.azamovhudstc.epolisinsurance.data.remote.request.SubmitPolicyRequest
import com.azamovhudstc.epolisinsurance.data.remote.response.policy.SubmitPolicyResponse
import kotlinx.coroutines.flow.Flow

interface ContactScreenUseCase{
    fun submitPolicy(submitPolicyRequest: SubmitPolicyRequest):Flow<Result<SubmitPolicyResponse>>
}