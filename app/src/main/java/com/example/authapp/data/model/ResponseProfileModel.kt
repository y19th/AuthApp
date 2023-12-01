package com.example.authapp.data.model

import com.example.authapp.domain.models.PaymentModel
import com.google.gson.annotations.SerializedName

data class ResponseProfileModel(
    @SerializedName("success") val success: String,
    @SerializedName("response") val response: List<ResponsePaymentsModel> = listOf()
)


fun List<ResponsePaymentsModel>.toListPaymentModel(): List<PaymentModel> {
    val newList = mutableListOf<PaymentModel>()
    this.forEach { newList.add(it.toPaymentModel()) }
    return newList
}
