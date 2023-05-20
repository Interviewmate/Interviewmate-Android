package com.example.domain.usecase.signup

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.signup.UserAuth
import kotlinx.coroutines.flow.Flow

interface SetLoginUseCase {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<ResponseUseCaseModel<UserAuth>>
}