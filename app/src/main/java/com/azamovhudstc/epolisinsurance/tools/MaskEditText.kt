package com.azamovhudstc.epolisinsurance.tools

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class MaskEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    private var isEditing = false

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isEditing) {
                    isEditing = false
                    return
                }

                isEditing = true
                val text = s.toString()

                val formattedText = formatText(text)
                setText(formattedText)
                setSelection(formattedText.length)
            }
        })
    }

    private fun formatText(text: String): String {
        val formatted = StringBuilder()
        var charCount = 0

        for (i in text.indices) {
            if (charCount >= 10) break // Only allow up to 10 characters

            val c = text[i]
            if (c.isDigit()) {
                if (charCount == 2 || charCount == 5) {
                    formatted.append(".")
                } else if (charCount == 8) {
                    formatted.append(" ")
                }
                formatted.append(c)
                charCount++
            }
        }

        return formatted.toString()
    }
}
