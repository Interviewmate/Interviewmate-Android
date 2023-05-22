package com.example.domain.usecase.mypage

import com.example.domain.repository.MyPageRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class PutPortfolioUseCaseImpl @Inject constructor(
    private val myPageRepository: MyPageRepository
) : PutPortfolioUseCase {
    override suspend fun invoke(url: String, file: File): Flow<Boolean> =
        myPageRepository.putPortfolio(url, file)
}