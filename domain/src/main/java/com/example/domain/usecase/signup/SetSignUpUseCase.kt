package com.example.domain.usecase.signup

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface SetSignUpUseCase {

    suspend operator fun invoke(
        nickname: String,
        email: String,
        password: String,
        job: String
    ): Flow<ResponseUseCaseModel<UserInfo>>

}