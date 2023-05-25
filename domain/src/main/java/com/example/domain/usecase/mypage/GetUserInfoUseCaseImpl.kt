package com.example.domain.usecase.mypage

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.mypage.MyPageUserInfo
import com.example.domain.model.signup.UserAuth
import com.example.domain.repository.MyPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(
    private val myPageRepository: MyPageRepository
) : GetUserInfoUseCase {
    override suspend fun invoke(userAuth: UserAuth): Flow<ResponseUseCaseModel<MyPageUserInfo>> =
        myPageRepository.getUserInfo(userAuth)
}