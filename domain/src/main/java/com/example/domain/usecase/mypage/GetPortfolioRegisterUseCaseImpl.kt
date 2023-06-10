package com.example.domain.usecase.mypage

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.signup.UserAuth
import com.example.domain.repository.MyPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPortfolioRegisterUseCaseImpl @Inject constructor(
    private val myPageRepository: MyPageRepository
) : GetPortfolioRegisterUseCase {
    override suspend fun invoke(userAuth: UserAuth, objectUrl: String): Flow<ResponseUseCaseModel<String>> =
        myPageRepository.getPortfolioRegister(userAuth.accessToken, userAuth.userId, objectUrl)
}