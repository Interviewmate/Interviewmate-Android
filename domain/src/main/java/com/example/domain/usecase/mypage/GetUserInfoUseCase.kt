package com.example.domain.usecase.mypage

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.mypage.MyPageUserInfo
import com.example.domain.model.signup.UserAuth
import kotlinx.coroutines.flow.Flow

interface GetUserInfoUseCase {
    suspend operator fun invoke(userAuth: UserAuth): Flow<ResponseUseCaseModel<MyPageUserInfo>>
}