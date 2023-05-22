package com.example.domain.usecase.mypage

import kotlinx.coroutines.flow.Flow
import java.io.File

interface PutPortfolioUseCase {
    suspend operator fun invoke(
        url: String,
        file: File
    ): Flow<Boolean>
}