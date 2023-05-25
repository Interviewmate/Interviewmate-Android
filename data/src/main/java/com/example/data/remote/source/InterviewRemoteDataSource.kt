package com.example.data.remote.source

import com.example.data.remote.model.ApiResponse
import com.example.data.remote.model.interview.*
import com.example.data.repository.model.ResponseRepositoryModel
import okhttp3.RequestBody
import retrofit2.Response

internal interface InterviewRemoteDataSource {

    suspend fun setInterview(
        accessToken: String,
        userId: UserId
    ): Result<ResponseRepositoryModel<InterviewId>>

    suspend fun getInterviewQuestions(
        accessToken: String,
        userId: Int,
        csKeyword: Array<String>
    ): Result<ResponseRepositoryModel<QuestionInfo>>

    suspend fun setS3PreSigned(
        preSignedInfo: PreSignedInfo
    ): Result<ResponseRepositoryModel<PreSignedUrl>>

    suspend fun putInterviewVideo(
        url: String,
        requestBody: RequestBody
    ): Result<Boolean>

    suspend fun setInterviewAnalyses(
        accessToken: String,
        interviewId: Int,
        objectKey: String,
        questionId: Int
    ): Result<ResponseRepositoryModel<String>>

    suspend fun setInterviewVideoUrl(
        accessToken: String,
        interviewId: Int,
        url: String
    ): Result<ResponseRepositoryModel<String>>
}