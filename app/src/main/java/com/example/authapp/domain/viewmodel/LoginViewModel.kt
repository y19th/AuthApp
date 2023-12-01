package com.example.authapp.domain.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.data.model.RequestLoginModel
import com.example.authapp.domain.events.LoginEvents
import com.example.authapp.domain.repositories.LoginRepository
import com.example.authapp.domain.states.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    companion object {
        const val TAG = "LoginViewModel"
    }

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val repository = LoginRepository()

    fun onEvent(event: LoginEvents) {
        when(event) {
            is LoginEvents.OnLoginChange -> {
                _state.update { it.copy(login = event.newLogin, isLoginError = false) }
            }
            is LoginEvents.OnPasswordChange -> {
                _state.update { it.copy(password = event.newPassword, isPasswordError = false) }
            }
            is LoginEvents.OnLoginError -> {
                _state.update { it.copy(isLoginError = true) }
            }
            is LoginEvents.OnPasswordError -> {
                _state.update { it.copy(isPasswordError = true) }
            }
            is LoginEvents.OnConfirm -> {
                if(isDataValid()) {
                    _state.update { it.copy(isLoading = true) }
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.login(
                            requestLoginModel = RequestLoginModel(
                                login = state.value.login,
                                password = state.value.password
                            ),
                            onSuccess = { response ->
                                Log.i(TAG,"response is $response")
                                if(response != null && response.success == "true") {
                                    _state.update { it.copy(isLoading = false) }
                                    event.onSuccess.invoke(
                                        response.response.token,
                                        state.value.login
                                    )
                                } else {
                                    _state.update { loginState->
                                        loginState.copy(isPasswordError = true, isLoginError = true, isLoading = false)
                                    }
                                }
                            },
                            onError = {
                                Log.i(TAG,"throwed ${it.message}")
                                _state.update { loginState->
                                    loginState.copy(isPasswordError = true, isLoginError = true, isLoading = false)
                                }
                            }
                        )
                    }
                }
            }
        }
    }


    private fun isDataValid() : Boolean {
        var isValid = true
        if(state.value.login.isEmpty()) {
            _state.update { it.copy(isLoginError = true) }
            isValid = false
        }
        if(state.value.password.isEmpty()) {
            _state.update { it.copy(isPasswordError = true) }
            isValid = false
        }
        return isValid
    }
}