package com.example.authapp.di

import com.example.authapp.domain.viewmodel.LoginViewModel
import com.example.authapp.domain.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        LoginViewModel()
    }

    viewModel {
        ProfileViewModel()
    }

}