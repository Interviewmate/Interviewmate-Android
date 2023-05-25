package com.example.domain.usecase.mypage

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.signup.UserAuth
import com.example.domain.repository.MyPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PutPortfolioKeywordUseCaseImpl @Inject constructor(
    private val myPageRepository: MyPageRepository
) : PutPortfolioKeywordUseCase {
    override suspend fun invoke(userAuth: UserAuth): Flow<ResponseUseCaseModel<String>> =
        myPageRepository.getPortfolioKeyword(userAuth.accessToken, userAuth.userId)
}