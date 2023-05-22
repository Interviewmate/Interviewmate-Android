package com.example.domain.repository

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.mypage.MyPageUserInfo
import kotlinx.coroutines.flow.Flow

interface MyPageRepository {
    suspend fun getUserInfo(userId: Int): Flow<ResponseUseCaseModel<MyPageUserInfo>>
}