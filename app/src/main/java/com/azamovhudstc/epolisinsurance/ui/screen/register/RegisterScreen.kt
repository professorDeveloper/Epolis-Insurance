package com.azamovhudstc.epolisinsurance.ui.screen.register

import android.Manifest
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.remote.request.RegisterRequest
import com.azamovhudstc.epolisinsurance.tools.hasConnection
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.viewmodel.AuthViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AuthViewModelImp
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register_screen.*
import kotlinx.android.synthetic.main.fragment_register_screen.view.*


@AndroidEntryPoint
class RegisterScreen :
    Fragment(R.layout.fragment_register_screen) {
    private val viewModel: AuthViewModel by viewModels<AuthViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorResponseLiveData.observe(this) {
            println("error")
            register_phone.isEnabled =true
            showToast(it.toString())
        }

        viewModel.registerResponseLiveData.observe(this) {
            Toast.makeText(requireContext(), getString(R.string.code_resend), Toast.LENGTH_SHORT)
                .show()
            PermissionX.init(this)
                .permissions(
                    Manifest.permission.READ_SMS,
                )
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        val bundle = Bundle()


                        bundle.putBoolean("isPermission", true)
                        bundle.putString("phone", "998${register_phone.text.toString()}")
                        findNavController().navigate(
                            R.id.otpScreen,
                            bundle,
                            animationTransaction().build()
                            )


                    } else {
                        showToast("Ruxsat berilmadi  :(")
                        val bundle = Bundle()
                        bundle.putString("phone", "998${register_phone.text.toString()}")
                        bundle.putBoolean("isPermission", false)
                        findNavController().navigate(
                            R.id.otpScreen,
                            bundle,
                            animationTransaction().build()

                        )

                    }
                }

        }
        viewModel.progressLiveData.observe(this) {
            if (it) {
                btn_Register_Txt.gone()
                visible_registeR_progress.visible()
            } else {
                visible_registeR_progress.gone()
                btn_Register_Txt.visible()

            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.send_otp.slideUp(777, 0)
        register_phone.phoneMask()
        view.register_phone.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                return true
            }
        })
        back_btn.setOnClickListener {
            findNavController().popBackStack()
        }
        register_phone.addTextChangedListener{
            if ("998${it}".extractString().checkPhone()) {
                if (hasConnection()) {
                    send_otp.requestFocus()
                } else {
                    showNetworkDialog(requireActivity(), null)
                }

            }
            else if (it.toString().length == 13) {
                register_phone.setText("")
                showToast(getString(R.string.error_phone))
            }

        }
        send_otp.setOnClickListener {
            if ("998${register_phone.text}".extractString().checkPhone()) {
                if (hasConnection()) {
                    register_phone.isEnabled = false
                    println("998${register_phone.text.toString().extractString()}")
                    viewModel.registerUser(
                        RegisterRequest(
                            "",
                            "998${register_phone.text.toString().extractString()}"
                        )
                    )
                } else {
                    showNetworkDialog(requireActivity(), null)
                }
            } else {

                showToast(getString(R.string.error_phone))

            }
        }
    }


}