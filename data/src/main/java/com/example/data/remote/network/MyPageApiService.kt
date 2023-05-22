package com.example.data.remote.network

import com.example.data.remote.model.ApiResponse
import com.example.data.remote.model.mypage.MyPageUserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MyPageApiService {
    @GET("users/info/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Int
    ): Response<ApiResponse<MyPageUserInfo>>
}