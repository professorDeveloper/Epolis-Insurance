package com.azamovhudstc.epolisinsurance.viewmodel

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface InfoDriversPageViewModel {
    val driverIndices : StateFlow<Int>
    val removedPage: StateFlow<Int>
    val showDriver: StateFlow<Int>
    val showSuccess: StateFlow<Int>
    val errorMessage: SharedFlow<String>
    fun loadInitData()
    fun addDriver()
    fun removeDriver(driverIndex: Int)
    fun showDriver(index: Int)
    fun showSuccess(index: Int)
}