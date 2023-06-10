package com.example.data.remote.network

import com.example.data.remote.model.ApiResponse
import com.example.data.remote.model.mypage.IsExist
import com.example.data.remote.model.mypage.MyPageUserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MyPageApiService {
    @GET("users/info/{userId}")
    suspend fun getUserInfo(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: Int
    ): Response<ApiResponse<MyPageUserInfo>>

    @GET("portfolios/keyword/{userId}")
    suspend fun getPortfolioKeyword(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: Int
    ): Response<ApiResponse<String>>

    @GET("portfolios/{userId}")
    suspend fun getPortfolioExist(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: Int
    ): Response<ApiResponse<IsExist>>

    @GET("portfolios/user/{userId}")
    suspend fun getPortfolioRegister(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: Int,
        @Query("objectUrl") objectUrl: String
    ): Response<ApiResponse<String>>
}