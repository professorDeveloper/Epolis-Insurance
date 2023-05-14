package com.azamovhudstc.epolisinsurance.ui.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import kotlinx.android.synthetic.main.fragment_profile_screen.*

class ProfileScreen : Fragment(R.layout.fragment_profile_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener(){
        openInfo.setOnClickListener {
            findNavController().navigate(R.id.editProfileScreen)
        }
    }

}