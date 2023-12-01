package com.example.authapp.ext

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun View.navigate(id: Int) {
    this.findNavController().navigate(id)
}

fun View.navigateBack() {
    this.findNavController().navigateUp()
}

fun View.navigate(navDirections: NavDirections) {
    this.findNavController().navigate(navDirections)
}