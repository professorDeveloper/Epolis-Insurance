package com.azamovhudstc.epolisinsurance.ui.screen.profile.edit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.LocalData.REQUEST_CODE
import kotlinx.android.synthetic.main.edi_profile_screen.*

class EditProfileScreen : Fragment(R.layout.edi_profile_screen) {
    var curFile: Uri? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        pickImage()
        clickListen()
    }

    private fun clickListen() {
        edit_profile_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
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
                curFile=it
                profile_picture.setImageURI(it)
            }
        }
    }

}