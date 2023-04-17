package com.example.domain.usecase.signup

import com.example.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface SetLoginUseCase {
    suspend operator fun invoke(email: String, password: String): Flow<LoginResponse>
}