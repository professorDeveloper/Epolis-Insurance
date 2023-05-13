package com.azamovhudstc.epolisinsurance.data.local.shp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.azamovhudstc.epolisinsurance.utils.enums.CurrentScreenEnum
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.sugurtaapp.utils.screenCurrentEnum
import com.azamovhudstc.sugurtaapp.utils.screenEnum
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppReference @Inject constructor(
    @ApplicationContext
    context: Context,
) {
    private var sharedPref: SharedPreferences = context.getSharedPreferences("impex_shp", MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPref.edit()


    var currentLanguage: LanguageType
        get() = sharedPref.getString("language", LanguageType.ru.name)!!.screenEnum()
        set(value) {
            sharedPref.edit().putString("language", value.name).apply()
        }
    var currentScreenEnum: CurrentScreenEnum
        get() = sharedPref.getString("current_language", CurrentScreenEnum.LANGUAGE.name)!!.screenCurrentEnum()
        set(value) {
            sharedPref.edit().putString("current_language", value.name).apply()
        }

    fun setToken(token: String) {
        editor.putString("TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString("TOKEN", "")
    }


    fun setDarkMode(token: Boolean) {
        editor.putBoolean("isDarkModeOn", token)
        editor.apply()
    }

    fun getDarkMode(): Boolean? {
        return sharedPref.getBoolean("isDarkModeOn", false)
    }

}