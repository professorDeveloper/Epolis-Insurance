package com.azamovhudstc.epolisinsurance.viewmodel.imp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.epolisinsurance.viewmodel.InfoDriversPageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoDriversPageViewModelImp @Inject constructor() : InfoDriversPageViewModel, ViewModel() {
    override val driverIndices = MutableStateFlow(1)
    override val removedPage = MutableStateFlow<Int>(-1)
    override val errorMessage = MutableSharedFlow<String>()

    override fun loadInitData() {
    }

    override fun addDriver() {
        if(driverIndices.value < 5) {
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
        removedPage.value = driverIndex
        driverIndices.value --
    }

    override fun showDriver(index: Int) {

    }

}

