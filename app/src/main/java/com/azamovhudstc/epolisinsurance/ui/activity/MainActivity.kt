package com.azamovhudstc.epolisinsurance.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azamovhudstc.epolisinsurance.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.statusBarColor= Color.parseColor("#EEEEEE")

        setContentView(R.layout.activity_main)
    }
}