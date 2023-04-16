package com.example.domain.usecase.signup

import com.example.domain.model.EmailResponse
import kotlinx.coroutines.flow.Flow

interface AuthenticateCodeUseCase {
    suspend operator fun invoke(email: String, code: String): Flow<EmailResponse>
}