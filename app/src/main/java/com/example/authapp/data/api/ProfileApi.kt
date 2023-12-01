package com.example.authapp.data.api

import com.example.authapp.data.model.ResponseProfileModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ProfileApi {

    @Headers("app-key: 12345","v: 1")
    @GET("payments")
    fun fetchPayments(
        @Header("token") token: String
    ) : Call<ResponseProfileModel>


}