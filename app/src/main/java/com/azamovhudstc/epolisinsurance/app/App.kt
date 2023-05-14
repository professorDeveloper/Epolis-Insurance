package com.azamovhudstc.epolisinsurance.app

import android.app.Application
import com.prongbang.localization.LocalizationApplication
import com.prongbang.localization.LocalizationManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : LocalizationApplication() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}