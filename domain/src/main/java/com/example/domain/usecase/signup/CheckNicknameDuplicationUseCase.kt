package com.example.domain.usecase.signup

import com.example.domain.model.ResponseUseCaseModel
import kotlinx.coroutines.flow.Flow

interface CheckNicknameDuplicationUseCase {
    suspend operator fun invoke(nickname: String): Flow<ResponseUseCaseModel<String>>
}