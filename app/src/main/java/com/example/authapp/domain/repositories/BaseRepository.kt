package com.example.authapp.domain.repositories

import com.example.authapp.util.RetrofitBuilder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseRepository: KoinComponent {
    protected val builder: RetrofitBuilder by inject()
}