package com.example.data.remote.network

import com.example.data.remote.model.ApiResponse
import com.example.data.remote.model.analysis.*
import com.example.data.remote.model.analysis.AnswerAnalysisInfo
import com.example.data.remote.model.analysis.DayInterviewInfo
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

    @GET("interviews/day")
    suspend fun getDayInterviews(
        @Header("Authorization") accessToken: String,
        @Query("userId") userId: Int,
        @Query("date") date: String
    ): Response<ApiResponse<List<DayInterviewInfo>>>

    @GET("analyses/check/{interviewId}")
    suspend fun getCheckAnalysisOver(
        @Header("Authorization") accessToken: String,
        @Path("interviewId") interviewId: Int
    ): Response<ApiResponse<String>>

    @GET("analyses/interview/{interviewId}")
    suspend fun getActionAnalysis(
        @Header("Authorization") accessToken: String,
        @Path("interviewId") interviewId: Int
    ): Response<ApiResponse<List<ActionAnalysisInfo>>>

    @GET("answers/{interviewId}")
    suspend fun getAnswerAnalysis(
        @Header("Authorization") accessToken: String,
        @Path("interviewId") interviewId: Int
    ): Response<ApiResponse<List<AnswerAnalysisInfo>>>

    @GET("analyses/{userId}")
    suspend fun getTotalAnalysis(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: Int
    ): Response<ApiResponse<List<TotalAnalysisInfo>>>

}