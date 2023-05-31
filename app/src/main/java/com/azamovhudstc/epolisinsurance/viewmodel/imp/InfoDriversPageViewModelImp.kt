package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.viewmodel.InfoDriversPageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoDriversPageViewModelImp @Inject constructor() : InfoDriversPageViewModel, ViewModel() {
    private val SIZE = 5
    override val driverIndices = MutableStateFlow(1)
    override val removedPage = MutableStateFlow(-1)
    override val showDriver = MutableStateFlow(-1)
    override val showSuccess = MutableStateFlow(-1)
    override val errorMessage = MutableSharedFlow<String>()

    override fun loadInitData() {
    }

    override fun addDriver() {
        if(driverIndices.value < SIZE) {
            driverIndices.value ++
            showDriver(driverIndices.value - 1)
        }
    }

    override fun removeDriver(driverIndex: Int) {
        if(driverIndices.value == 1) {
            viewModelScope.launch {
                errorMessage.emit("You should add at least 1 driver")
            }
            return
        }
        driverIndices.value --
        removedPage.value = driverIndex
    }

    override fun showDriver(index: Int) {
        showDriver.value = index
    }

    override fun showSuccess(index: Int) {
        showSuccess.value = index
    }

}

