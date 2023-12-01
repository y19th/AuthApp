package com.example.authapp.ext

import androidx.fragment.app.Fragment

fun Fragment.string(id: Int): String {
    return requireContext().getString(id)
}

fun Fragment.string(id: Int, vararg args:String): String {
    return requireContext().getString(id,args)
}