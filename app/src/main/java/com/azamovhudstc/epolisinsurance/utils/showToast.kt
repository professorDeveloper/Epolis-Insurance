package com.azamovhudstc.epolisinsurance.utils

import com.azamovhudstc.epolisinsurance.R
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog

fun showToast(message: String) {
    TipDialog.show("$message!", WaitDialog.TYPE.ERROR, 2000)
        .setBackgroundColorRes(R.color.btn_color)

}

fun showToastSuccess(message: String) {
    TipDialog.show("$message!", WaitDialog.TYPE.SUCCESS, 2000)
        .setBackgroundColorRes(R.color.btn_color)

}

fun state(bool: Boolean) {
    if (bool) {
        WaitDialog.show("Please Wait!").setBackgroundColorRes(R.color.btn_color)
    } else {
        WaitDialog.dismiss()
    }
}



