package com.example.authapp.domain.repositories

import com.example.authapp.data.api.LoginApi
import com.example.authapp.data.model.RequestLoginModel
import com.example.authapp.data.model.ResponseLoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository: BaseRepository() {

    fun login(
        requestLoginModel: RequestLoginModel,
        onSuccess: (ResponseLoginModel?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        builder.instance(LoginApi::class.java).login(requestLoginModel).enqueue(
            object: Callback<ResponseLoginModel> {
                override fun onResponse(
                    call: Call<ResponseLoginModel>,
                    response: Response<ResponseLoginModel>
                ) {
                    onSuccess.invoke(response.body())
                }

                override fun onFailure(call: Call<ResponseLoginModel>, t: Throwable) {
                    onError.invoke(t)
                }
            }
        )
    }
}