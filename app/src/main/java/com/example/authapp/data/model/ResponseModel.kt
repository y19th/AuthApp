package com.example.authapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(@SerializedName("token") val token: String = "")