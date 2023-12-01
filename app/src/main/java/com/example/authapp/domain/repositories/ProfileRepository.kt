package com.example.authapp.domain.repositories

import com.example.authapp.data.api.ProfileApi
import com.example.authapp.data.model.ResponseProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository: BaseRepository() {

    fun fetchPayments(
        token: String,
        onSuccess: (ResponseProfileModel?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        builder.instance(ProfileApi::class.java).fetchPayments(token).enqueue(
            object: Callback<ResponseProfileModel> {
                override fun onResponse(
                    call: Call<ResponseProfileModel>,
                    response: Response<ResponseProfileModel>
                ) {
                    onSuccess.invoke(response.body())
                }

                override fun onFailure(call: Call<ResponseProfileModel>, t: Throwable) {
                    onError.invoke(t)
                }

            }
        )
    }

}