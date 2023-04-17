package com.example.data.remote.network.signup

import com.example.data.remote.model.signup.EmailResponse
import com.example.data.remote.model.signup.LoginResponse
import com.example.data.remote.model.signup.SignUpResponse
import com.example.data.remote.model.signup.SignUpUserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface SignUpApiService {

    @POST("users/sign-up")
    suspend fun setSignUp(
        @Body signUpUserInfo: SignUpUserInfo
    ): Result<SignUpResponse>

    @GET("/auth/email")
    suspend fun sendEmail(
        @Query("toEmail") email: String
    ): Result<EmailResponse>

    @GET("/auth/authCode")
    suspend fun authenticateCode(
        @Query("email") email: String,
        @Query("code") code: String
    ): Result<EmailResponse>

    @POST("/auth/login")
    suspend fun setLogin(
        @Query("email") email: String,
        @Query("password") password: String
    ): Result<LoginResponse>
}