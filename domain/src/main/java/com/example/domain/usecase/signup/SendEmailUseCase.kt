package com.example.domain.usecase.signup

import com.example.domain.model.ResponseUseCaseModel
import kotlinx.coroutines.flow.Flow

interface SendEmailUseCase {
    suspend operator fun invoke(email: String): Flow<ResponseUseCaseModel<String>>
}