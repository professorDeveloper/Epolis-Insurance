package com.azamovhudstc.epolisinsurance.ui.screen.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.invisible
import com.azamovhudstc.epolisinsurance.utils.visible
import com.azamovhudstc.epolisinsurance.viewmodel.ProfileScreenViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.ProfileScreenViewModelImp
import com.azamovhudstc.sugurtaapp.utils.stringToBitmap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile_screen.*

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.fragment_profile_screen) {

    private val viewModel: ProfileScreenViewModel by viewModels<ProfileScreenViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadProfileData.observe(this, loadProfileDataObserver)
        viewModel.errorLoadProfileData.observe(this, errorLoadProfileData)
        viewModel.successProfileData.observe(this, successProfileData)
        viewModel.logoutSuccessLiveData.observe(this) {
            findNavController().navigate(
                R.id.mainScreen,
                null,
                NavOptions.Builder().setPopUpTo(R.id.mainScreen, true).build()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        viewModel.loadProfile()
    }

    private fun initClickListener() {
        openInfo.setOnClickListener {
            findNavController().navigate(R.id.editProfileScreen)
        }
        contact_us_btn.setOnClickListener {
            findNavController().navigate(R.id.contactUsScreen)
        }
        constraintLayout.setOnClickListener {
            findNavController().navigate(R.id.registerScreen)
        }
        language_setting.setOnClickListener {
            findNavController().navigate(R.id.langaugeScreen)
        }
        logout_btn.setOnClickListener {
            viewModel.logout()
        }
    }

    private val loadProfileDataObserver = Observer<String> {
        profile_container.visible()
        constraintLayout.gone()
        openInfo.isEnabled = true
        phone_profile.text = it
        logout_btn.visible()
        openInfo.visible()
    }

    private val errorLoadProfileData = Observer<String> {
        constraintLayout.visible()
        profile_container.invisible()
        logout_btn.invisible()
        openInfo.gone()
        openInfo.isEnabled = false
    }

    @SuppressLint("SetTextI18n")
    private val successProfileData = Observer<ProfileEntity> {
        if (it.lastName.isNotEmpty() && it.name.isNotEmpty()) {
            textView2.text = "${it.name} ${it.lastName}"
        }
        profile_circle_image.setImageBitmap(it.photoUri.stringToBitmap())
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadProfile()
    }
}