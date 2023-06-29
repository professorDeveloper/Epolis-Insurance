package com.azamovhudstc.epolisinsurance.utils

import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    TipDialog.show("$message!", WaitDialog.TYPE.ERROR, 2000)
        .setBackgroundColorRes(R.color.btn_color)

}
fun Fragment.showToastError(
    title: String?,
    description: String,
    style: MotionToastStyle
) {
    MotionToast.createColorToast(
        requireActivity(),
        title,
        description,
        style,

        MotionToast.GRAVITY_TOP or MotionToast.GRAVITY_CENTER,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(requireContext(), R.font.urbanist_font_family)
    )}

fun Fragment.showToastSuccess(message: String, duration: Int = Toast.LENGTH_SHORT) {
    TipDialog.show("$message!", WaitDialog.TYPE.SUCCESS, 2000)
        .setBackgroundColorRes(R.color.btn_color)

}

fun Fragment.state(bool: Boolean) {
    if (bool) {
        WaitDialog.show("Please Wait!").setBackgroundColorRes(R.color.btn_color)
    } else {
        WaitDialog.dismiss()
    }
}



