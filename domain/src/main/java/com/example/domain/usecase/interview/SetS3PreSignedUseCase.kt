package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.PreSignedUrl
import kotlinx.coroutines.flow.Flow

interface SetS3PreSignedUseCase {
    suspend operator fun invoke(
        userId: Int,
        number: Int,
        directory: String
    ): Flow<ResponseUseCaseModel<PreSignedUrl>>
}