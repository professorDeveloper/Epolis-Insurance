package com.azamovhudstc.epolisinsurance.ui.screen.profile.contact_admin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.ui.adapter.ContactUsAdapter
import com.azamovhudstc.epolisinsurance.utils.LocalData
import kotlinx.android.synthetic.main.contact_us_screen.*

class ContactUsScreen : Fragment(R.layout.contact_us_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contact_us_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        var adapter=ContactUsAdapter()
        adapter.submitList(LocalData.loadContactUs())
        contactus_rv.adapter=adapter
    }
}