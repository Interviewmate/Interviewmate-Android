package com.example.data.remote.network

import com.example.data.remote.model.ApiResponse
import com.example.data.remote.model.interview.InterviewInfo
import com.example.data.remote.model.interview.UserId
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

internal interface InterviewApiService {
    @POST("interviews/")
    suspend fun setInterview(
        @Header("Authorization") accessToken:String,
        @Body userId: UserId
    ): Response<ApiResponse<InterviewInfo>>
}