package com.azamovhudstc.epolisinsurance.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    TipDialog.show("$message!", WaitDialog.TYPE.ERROR, 2000)
        .setBackgroundColorRes(R.color.btn_color)

}
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



