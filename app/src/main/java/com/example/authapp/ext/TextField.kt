package com.example.authapp.ext

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.error(parent: TextInputLayout, isError: Boolean,text: String = "") {
    parent.error = if(isError) text else null
}