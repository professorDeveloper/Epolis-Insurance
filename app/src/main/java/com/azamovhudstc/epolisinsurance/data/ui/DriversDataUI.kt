package com.azamovhudstc.epolisinsurance.data.ui

import com.azamovhudstc.epolisinsurance.utils.enums.DriversType
data class DriversDataUI(
    var oneDriver:DriversType,
    var twoDriver:DriversType,
    var thirdDriver:DriversType,
    var fourDriver:DriversType,
    var fiveDriver:DriversType
){
    fun driversIsAllNew():Boolean{
        return !(oneDriver==DriversType.NEW&&twoDriver==DriversType.NEW && thirdDriver==DriversType.NEW&& fourDriver==DriversType.NEW && fiveDriver==DriversType.NEW)
    }
    fun getDataUIbyPosition(position:Int):DriversType{
      return  when(position){
            0-> oneDriver
            1-> twoDriver
            2-> thirdDriver
            3-> fourDriver
            else-> fiveDriver
        }
    }
}