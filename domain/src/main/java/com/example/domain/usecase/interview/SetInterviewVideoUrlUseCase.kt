package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import kotlinx.coroutines.flow.Flow

interface SetInterviewVideoUrlUseCase {
    suspend operator fun invoke(
        accessToken: String,
        interviewId: Int,
        url: String
    ): Flow<ResponseUseCaseModel<String>>
}