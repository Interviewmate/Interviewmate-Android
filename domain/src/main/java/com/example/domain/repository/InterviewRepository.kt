package com.example.domain.repository

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.InterviewId
import com.example.domain.model.interview.QuestionInfo
import com.example.domain.model.interview.UserId
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
}