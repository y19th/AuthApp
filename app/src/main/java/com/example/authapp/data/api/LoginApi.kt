package com.example.authapp.data.api

import com.example.authapp.data.model.RequestLoginModel
import com.example.authapp.data.model.ResponseLoginModel
import retrofit2.Call
import retrofit2.http.Body

import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @Headers("app-key: 12345","v: 1")
    @POST("login")
    fun login(
        @Body requestLoginModel: RequestLoginModel
    ) : Call<ResponseLoginModel>

}