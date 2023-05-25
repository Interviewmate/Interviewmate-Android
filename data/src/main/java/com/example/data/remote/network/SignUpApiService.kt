package com.example.data.remote.network

import com.example.data.remote.model.ApiResponse
import com.example.data.remote.model.signup.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface SignUpApiService {

    @POST("users/sign-up")
    suspend fun setSignUp(
        @Body signUpUserInfo: SignUpUserInfo
    ): Response<ApiResponse<UserInfo>>

    @GET("/auth/email")
    suspend fun sendEmail(
        @Query("toEmail") email: String
    ): Response<ApiResponse<String>>

    @GET("/auth/authCode")
    suspend fun authenticateCode(
        @Query("email") email: String,
        @Query("code") code: String
    ): Response<ApiResponse<String>>

    @POST("/auth/login")
    suspend fun setLogin(
        @Body loginUserInfo: LoginUserInfo
    ): Response<ApiResponse<UserAuth>>

    @GET("/auth/check")
    suspend fun checkNicknameDuplication(
        @Query("nickName") nickname: String
    ): Response<ApiResponse<String>>

    @POST("/keywords/user")
    suspend fun setKeywords(
        @Body userKeyword: UserKeyword
    ): Response<ApiResponse<String>>

}