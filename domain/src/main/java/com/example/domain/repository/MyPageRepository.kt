package com.example.domain.repository

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.mypage.MyPageUserInfo
import com.example.domain.model.signup.UserAuth
import kotlinx.coroutines.flow.Flow

interface MyPageRepository {
    suspend fun getUserInfo(userAuth: UserAuth): Flow<ResponseUseCaseModel<MyPageUserInfo>>
}