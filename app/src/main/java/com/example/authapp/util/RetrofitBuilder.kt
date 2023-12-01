package com.example.authapp.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://easypay.world/api-test/"

class RetrofitBuilder {


    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> instance(service: Class<T>): T {
        return retrofitBuilder.create(service)
    }

}