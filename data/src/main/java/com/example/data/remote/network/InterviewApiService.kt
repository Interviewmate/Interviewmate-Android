package com.example.data.remote.network

import com.example.data.remote.model.ApiResponse
import com.example.data.remote.model.interview.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

internal interface InterviewApiService {
    @POST("interviews/")
    suspend fun setInterview(
        @Header("Authorization") accessToken: String,
        @Body userId: UserId
    ): Response<ApiResponse<InterviewId>>

    @GET("questions/{userId}")
    suspend fun getInterviewQuestions(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: Int,
        @Query("questionNum") questionNum: Int = QUESTION_NUM,
        @Query("csKeyword") csKeyword: Array<String>
    ): Response<ApiResponse<QuestionInfo>>

    @POST("s3/pre-signed")
    suspend fun setS3PreSigned(
        @Body preSignedInfo: PreSignedInfo
    ): Response<ApiResponse<PreSignedUrl>>

    @PUT
    suspend fun putInterviewVideo(
        @Url url: String,
        @Body requestBody: RequestBody
    ): ResponseBody

    @POST("analyses/{interviewId}")
    suspend fun setInterviewAnalyses(
        @Header("Authorization") accessToken: String,
        @Path("interviewId") interviewId: Int,
        @Query("objectKey") objectKey: String
    ): Response<ApiResponse<String>>

    companion object {
        const val QUESTION_NUM = 10
    }
}