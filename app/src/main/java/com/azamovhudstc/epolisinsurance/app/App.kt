package com.azamovhudstc.epolisinsurance.app

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import com.prongbang.localization.LocalizationApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : LocalizationApplication() {
    companion object {
        lateinit var instance: App

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

}