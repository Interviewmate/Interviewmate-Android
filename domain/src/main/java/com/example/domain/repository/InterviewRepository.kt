package com.example.domain.repository

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.*
import kotlinx.coroutines.flow.Flow

interface InterviewRepository {
    suspend fun setInterview(
        accessToken: String,
        userId: UserId
    ): Flow<ResponseUseCaseModel<InterviewId>>

    suspend fun getInterviewQuestions(
        accessToken: String,
        userId: Int,
        csKeyword: Array<String>
    ): Flow<ResponseUseCaseModel<QuestionInfo>>

    suspend fun setS3PreSigned(
        preSignedInfo: PreSignedInfo
    ): Flow<ResponseUseCaseModel<PreSignedUrl>>

    suspend fun putInterviewVideo(
        url: String,
        filePath: String
    ): Flow<Boolean>

    suspend fun setInterviewAnalyses(
        accessToken: String,
        interviewId: Int,
        objectKey: String,
        questionId: Int
    ): Flow<ResponseUseCaseModel<String>>

    suspend fun setInterviewVideoUrl(
        accessToken: String,
        interviewId: Int,
        url: String
    ): Flow<ResponseUseCaseModel<String>>

}