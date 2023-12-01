package com.example.authapp.di

import com.example.authapp.util.RetrofitBuilder
import org.koin.dsl.module

val mainModule = module {
    single {
        RetrofitBuilder()
    }
}