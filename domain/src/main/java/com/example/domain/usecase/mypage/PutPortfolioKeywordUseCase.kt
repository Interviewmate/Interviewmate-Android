package com.example.domain.usecase.mypage

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.signup.UserAuth
import kotlinx.coroutines.flow.Flow

interface PutPortfolioKeywordUseCase {
    suspend operator fun invoke(
        userAuth: UserAuth
    ): Flow<ResponseUseCaseModel<String>>
}