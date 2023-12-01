package com.example.authapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseLoginModel(
    @SerializedName("success") val success: String = "",
    @SerializedName("error") val error: ResponseErrorModel = ResponseErrorModel(),
    @SerializedName("response") val response: ResponseModel = ResponseModel()
)
