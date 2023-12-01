package com.example.authapp.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.toDate(): String {
    val temp = this.toLongOrNull()
    return if(temp != null) {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = temp
        formatter.format(calendar.time)
    } else ""
}