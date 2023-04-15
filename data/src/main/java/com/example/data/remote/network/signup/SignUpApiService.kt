package com.example.data.remote.network.signup

import com.example.data.remote.model.signup.EmailResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface SignUpApiService {

    @POST
    suspend fun setSignUp()

    @GET("/auth/email")
    suspend fun sendEmail(
        @Query("toEmail") email: String
    ): Result<EmailResponse>
}