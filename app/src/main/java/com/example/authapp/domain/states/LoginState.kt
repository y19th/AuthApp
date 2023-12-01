package com.example.authapp.domain.states

data class LoginState(
    val login: String = "",
    val password: String = "",

    val isLoginError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isLoading: Boolean = false
)
