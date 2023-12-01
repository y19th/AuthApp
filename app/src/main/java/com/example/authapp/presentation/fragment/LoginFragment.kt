package com.example.authapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.authapp.R
import com.example.authapp.databinding.FragmentMainBinding
import com.example.authapp.domain.events.LoginEvents
import com.example.authapp.domain.states.LoginState
import com.example.authapp.domain.viewmodel.LoginViewModel
import com.example.authapp.ext.error
import com.example.authapp.ext.navigate
import com.example.authapp.ext.string
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginFragment: BaseFragment<FragmentMainBinding>() {

    private val viewModel get() =  getViewModel<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    attachUi(it)
                }
            }
        }
    }

    private fun attachUi(data: LoginState) {
        binding.apply {
            loader(data.isLoading)
            loginTextField.also {
                it.doOnTextChanged { _, _, _, _ ->
                    viewModel.onEvent(LoginEvents.OnLoginChange(loginTextField.text.toString()))
                }
            }.error(loginTextLayout,data.isLoginError,string(R.string.login_error))
            passwordTextField.also {
                it.doOnTextChanged { _, _, _, _ ->
                    viewModel.onEvent(LoginEvents.OnPasswordChange(passwordTextField.text.toString()))
                }
            }.error(passwordTextLayout,data.isPasswordError,string(R.string.password_error))
            confirmationButton.setOnClickListener {
                viewModel.onEvent(LoginEvents.OnConfirm(
                    onSuccess = { token, login ->
                        it.navigate(LoginFragmentDirections.actionMainFragmentToProfileFragment(token, login))
                    }
                ))
            }
        }
    }

    private fun loader(enabled: Boolean) {
        binding.apply {
            if (enabled) {
                confirmationButton.isEnabled = false
                progressLoader.visibility = View.VISIBLE
                loader.isIndeterminate = true
            } else {
                confirmationButton.isEnabled = true
                progressLoader.visibility = View.INVISIBLE
                loader.isIndeterminate = false
            }
        }
    }

}