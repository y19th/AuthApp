package com.example.authapp.domain.states

import com.example.authapp.domain.models.PaymentModel

data class ProfieState(
    val login: String = "",
    val payments: List<PaymentModel> = emptyList(),

    val isLoading: Boolean = false
)
