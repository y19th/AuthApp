package com.example.authapp.data.model

import com.example.authapp.domain.models.PaymentModel
import com.google.gson.annotations.SerializedName

data class ResponsePaymentsModel(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("title") val title: String = "",
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("created") val created: Long? = null
) {
    fun toPaymentModel() = PaymentModel(id, title, amount, created)
}