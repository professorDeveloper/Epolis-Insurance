package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import kotlinx.android.synthetic.main.drive_item.*

class DriverItemPage(
    private val onRemoveItem: () -> Unit
):Fragment(R.layout.drive_item) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clear_response.setOnClickListener { onRemoveItem() }
    }
}