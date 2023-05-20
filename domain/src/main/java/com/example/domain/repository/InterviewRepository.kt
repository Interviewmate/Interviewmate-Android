package com.example.domain.repository

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.InterviewInfo
import com.example.domain.model.interview.UserId
import kotlinx.coroutines.flow.Flow

interface InterviewRepository {
    suspend fun setInterview(accessToken: String, userId: UserId): Flow<ResponseUseCaseModel<InterviewInfo>>
}