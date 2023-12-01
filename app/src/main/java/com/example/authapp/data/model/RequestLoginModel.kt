package com.example.authapp.data.model

import com.google.gson.annotations.SerializedName

data class RequestLoginModel(
    @SerializedName("login") val login: String = "",
    @SerializedName("password") val password: String = ""
)