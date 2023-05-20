package com.example.data.remote.source

import com.example.data.remote.model.interview.InterviewId
import com.example.data.remote.model.interview.QuestionInfo
import com.example.data.remote.model.interview.UserId
import com.example.data.repository.model.ResponseRepositoryModel

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

}