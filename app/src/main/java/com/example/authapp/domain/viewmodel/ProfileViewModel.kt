package com.example.authapp.domain.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.data.model.toListPaymentModel
import com.example.authapp.domain.repositories.ProfileRepository
import com.example.authapp.domain.states.ProfieState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    companion object {
        const val TAG = "ProfileViewModel"
    }

    private val _state = MutableStateFlow(ProfieState())
    val state = _state.asStateFlow()

    private val repository = ProfileRepository()


    fun fetchPayments(token: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchPayments(
                token = token,
                onSuccess = { response ->
                    Log.i(TAG,"response is $response")
                    if(response != null && response.success == "true") {
                        _state.update {
                            it.copy(payments = response.response.toListPaymentModel(), isLoading = false)
                        }
                    }
                },
                onError = {
                    Log.i(TAG,"throwed ${it.message}")
                    _state.update { it.copy(isLoading = false) }
                }
            )
        }
    }
}