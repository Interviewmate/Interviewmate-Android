package com.example.domain.usecase.mypage

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.mypage.IsExist
import com.example.domain.model.signup.UserAuth
import com.example.domain.repository.MyPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPortfolioExistUseCaseImpl @Inject constructor(
    private val myPageRepository: MyPageRepository
) : GetPortfolioExistUseCase {
    override suspend fun invoke(userAuth: UserAuth): Flow<ResponseUseCaseModel<IsExist>> =
        myPageRepository.getPortfolioExist(userAuth.accessToken, userAuth.userId)
}