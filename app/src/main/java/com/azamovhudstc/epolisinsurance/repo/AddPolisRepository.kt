package com.azamovhudstc.epolisinsurance.repo

import kotlinx.coroutines.flow.Flow

interface AddPolisRepository {
    fun loadAddSpinnerList():Flow<ArrayList<String>>
}