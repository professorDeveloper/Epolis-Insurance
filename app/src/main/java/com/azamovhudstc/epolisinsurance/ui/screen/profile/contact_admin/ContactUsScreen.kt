package com.azamovhudstc.epolisinsurance.ui.screen.profile.contact_admin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import kotlinx.android.synthetic.main.contact_us_screen.*
import kotlinx.android.synthetic.main.fragment_profile_screen.*

class ContactUsScreen : Fragment(R.layout.contact_us_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contact_us_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}