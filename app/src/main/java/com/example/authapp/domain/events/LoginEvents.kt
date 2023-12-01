package com.example.authapp.domain.events

sealed interface LoginEvents {

    data object OnLoginError: LoginEvents

    data object OnPasswordError: LoginEvents

    data class OnConfirm(val onSuccess: (String,String) -> Unit): LoginEvents

    data class OnLoginChange(val newLogin: String) : LoginEvents

    data class OnPasswordChange(val newPassword: String) : LoginEvents

}