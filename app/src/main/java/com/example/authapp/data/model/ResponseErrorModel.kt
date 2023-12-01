package com.example.authapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseErrorModel (
    @SerializedName("error_code") val errorCode: Int = 0,
    @SerializedName("error_msg") val errorMessage: String = ""
)