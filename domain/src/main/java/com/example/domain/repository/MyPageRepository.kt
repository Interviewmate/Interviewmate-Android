package com.example.domain.repository

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.mypage.MyPageUserInfo
import com.example.domain.model.signup.UserAuth
import kotlinx.coroutines.flow.Flow
import java.io.File

interface MyPageRepository {
    suspend fun getUserInfo(userAuth: UserAuth): Flow<ResponseUseCaseModel<MyPageUserInfo>>

    suspend fun putPortfolio(
        url: String,
        file: File
    ): Flow<Boolean>

    suspend fun getPortfolioKeyword(
        accessToken: String,
        userId: Int
    ): Flow<ResponseUseCaseModel<String>>
}