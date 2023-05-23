package com.azamovhudstc.epolisinsurance.ui.screen.profile.edit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.room.entity.ProfileEntity
import com.azamovhudstc.epolisinsurance.utils.LocalData.REQUEST_CODE
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.visible
import com.azamovhudstc.epolisinsurance.viewmodel.imp.EditProfileScreenViewModelImp
import com.azamovhudstc.sugurtaapp.utils.stringToBitmap
import com.azamovhudstc.sugurtaapp.utils.toStringWithBitmap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.edi_profile_screen.*
import kotlinx.android.synthetic.main.succes_otp_screen.*


@AndroidEntryPoint
class EditProfileScreen : Fragment(R.layout.edi_profile_screen) {

    private val viewModel by viewModels<EditProfileScreenViewModelImp>()

    var curFile: Uri? = null
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this){
            if (it) {
                btn_save_profile_progress.visible()
                btn_save_profile.gone()
            }
            else {
                btn_save_profile_progress.gone()
                btn_save_profile.visible()
            }
        }
        viewModel.phoneLiveData.observe(this, phoneLoadObserver)
        viewModel.successEditLiveData.observe(this, successEditProfileObserver)
        viewModel.successProfileLiveData.observe(this, successProfileObserver)
    }

    private val phoneLoadObserver = Observer<String> {
        register_phone.text = it
    }

    private val successEditProfileObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private val successProfileObserver = Observer<ProfileEntity> {
        profile_picture.setImageBitmap(it.photoUri.stringToBitmap())
        edit_profile_name_txt.setText(it.name)
        edit_profile_last_name_txt.setText(it.lastName)
        bitmap = it.photoUri.stringToBitmap()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        viewModel.loadPhone()
        viewModel.initProfileData()
        pickImage()
        clickListen()


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun clickListen() {
        edit_profile_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        save_profile.setOnClickListener {
            if (this::bitmap.isInitialized) {
                viewModel.editProfile(
                    ProfileEntity(
                        photoUri = bitmap.toStringWithBitmap(),
                        name = edit_profile_name_txt.text.toString(),
                        lastName = edit_profile_last_name_txt.text.toString()
                    )
                )
            } else {
                val bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_edit);
                viewModel.editProfile(
                    ProfileEntity(
                        photoUri = bitmap.toStringWithBitmap(),
                        name = edit_profile_name_txt.text.toString(),
                        lastName = edit_profile_last_name_txt.text.toString()
                    )
                )
            }
        }
    }

    private fun pickImage() {
        pick_profile.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, REQUEST_CODE)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            data?.data?.let {
                curFile = it
                bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), it)
                profile_picture.setImageURI(it)
            }
        }
    }

}