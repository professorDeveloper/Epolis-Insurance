package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import kotlinx.android.synthetic.main.drive_item.*

class DriverItemPage : Fragment(R.layout.drive_item) {
    private var onSuccess: (() -> Unit)? = null
    private var onRemove: (() -> Unit)? = null
    private var index = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        successBtn.text = "Success $index"
        successBtn.setOnClickListener {
            Log.d("TTT", "success clicked")
            onSuccess?.invoke()
        }
        removeBtn.text = "Remove $index"
        removeBtn.setOnClickListener {
            onRemove?.invoke()
        }
    }

    fun setOnSuccess(action: () -> Unit) {
        onSuccess = action
    }

    fun setOnRemove(action: () -> Unit) {
        onRemove = action
    }

    fun setIndex(index: Int) {
        this.index = index
    }
}