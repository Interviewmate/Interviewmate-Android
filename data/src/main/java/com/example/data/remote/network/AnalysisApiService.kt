package com.example.data.remote.network

import com.example.data.remote.model.ApiResponse
import com.example.data.remote.model.analysis.MonthInterviewInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

internal interface AnalysisApiService {
    @GET("interviews/month")
    suspend fun getMonthInterviews(
        @Header("Authorization") accessToken: String,
        @Query("userId") userId: Int,
        @Query("yearMonth") yearMonth: String
    ): Response<ApiResponse<MonthInterviewInfo>>
}