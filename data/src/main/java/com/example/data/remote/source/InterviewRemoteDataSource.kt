package com.example.data.remote.source

import com.example.data.remote.model.interview.*
import com.example.data.repository.model.ResponseRepositoryModel
import okhttp3.RequestBody

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
    )

}