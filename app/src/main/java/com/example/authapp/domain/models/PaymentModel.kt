package com.example.authapp.domain.models

data class PaymentModel(
   val id: Int = -1,
   val title: String = "",
   val amount: String? = null,
   val created: Long? = null
)