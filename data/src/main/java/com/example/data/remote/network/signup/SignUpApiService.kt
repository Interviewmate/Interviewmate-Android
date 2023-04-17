package com.example.data.remote.network.signup

import com.example.data.remote.model.signup.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface SignUpApiService {

    @POST("users/sign-up")
    suspend fun setSignUp(
        @Body signUpUserInfo: SignUpUserInfo
    ): Result<Response<UserInfo>>

    @GET("/auth/email")
    suspend fun sendEmail(
        @Query("toEmail") email: String
    ): Result<Response<String>>

    @GET("/auth/authCode")
    suspend fun authenticateCode(
        @Query("email") email: String,
        @Query("code") code: String
    ): Result<Response<String>>

    @POST("/auth/login")
    suspend fun setLogin(
        @Body loginUserInfo: LoginUserInfo
    ): Result<Response<UserAuth>>
}