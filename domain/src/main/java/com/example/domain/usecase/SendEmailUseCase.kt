package com.example.domain.usecase

import com.example.domain.model.EmailResponse
import kotlinx.coroutines.flow.Flow

interface SendEmailUseCase {
    suspend operator fun invoke(email: String): Flow<EmailResponse>
}