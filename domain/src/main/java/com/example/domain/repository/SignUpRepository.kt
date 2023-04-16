package com.example.domain.repository

import com.example.domain.model.EmailResponse
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {
    suspend fun sendEmail(email: String): Flow<EmailResponse>

    suspend fun authenticateCode(email: String, code: String): Flow<EmailResponse>

}