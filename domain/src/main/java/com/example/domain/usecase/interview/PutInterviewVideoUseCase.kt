package com.example.domain.usecase.interview

import kotlinx.coroutines.flow.Flow

interface PutInterviewVideoUseCase {
    suspend operator fun invoke(
        url: String,
        filePath: String
    ): Flow<Boolean>
}