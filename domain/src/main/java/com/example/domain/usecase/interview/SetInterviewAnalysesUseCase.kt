package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import kotlinx.coroutines.flow.Flow

interface SetInterviewAnalysesUseCase {
    suspend operator fun invoke(accessToken: String, interviewId: Int, objectKey: String): Flow<ResponseUseCaseModel<String>>
}