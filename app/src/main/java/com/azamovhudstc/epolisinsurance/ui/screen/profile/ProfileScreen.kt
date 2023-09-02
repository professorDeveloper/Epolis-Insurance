package com.azamovhudstc.epolisinsurance.ui.screen.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.ui.screen.profile.language.LanguageActivity
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.viewmodel.ProfileScreenViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.ProfileScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile_screen.*

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.fragment_profile_screen) {

    private val viewModel: ProfileScreenViewModel by viewModels<ProfileScreenViewModelImp>()
    fun stringnull():String{
        return null!!
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewModel.loadProfileData.observe(this, loadProfileDataObserver)
        viewModel.errorLoadProfileData.observe(this, errorLoadProfileData)
        viewModel.successProfileData.observe(this, successProfileData)
        viewModel.logoutSuccessLiveData.observe(this) {
            AppReference(requireContext()).token="null"
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
            findNavController().navigate(R.id.editProfileScreen,args = null, animationTransaction().build())
        }
        contact_us_btn.setOnClickListener {
            findNavController().navigate(R.id.contactUsScreen,args = null, animationTransaction().build())
        }
        constraintLayout.setOnClickListener {
            findNavController().navigate(R.id.registerScreen,args = null, animationTransaction().build())
        }
        language_setting.setOnClickListener {
            val intent = Intent(requireActivity(), LanguageActivity::class.java)
            startActivity(intent)
            requireActivity().finish()

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
        if (AppReference(requireContext()).currentLanguage==LanguageType.uz){
            currentLanguage.setText("O`zbek tili")
        }
        else{
            currentLanguage.setText("русский язык")
        }
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