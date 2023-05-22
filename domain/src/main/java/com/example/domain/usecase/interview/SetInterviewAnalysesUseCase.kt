package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import kotlinx.coroutines.flow.Flow

interface SetInterviewAnalysesUseCase {
    suspend operator fun invoke(interviewId: Int, objectKey: String): Flow<ResponseUseCaseModel<String>>
}