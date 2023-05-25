package com.example.data.remote.source

import com.example.data.remote.model.mypage.IsExist
import com.example.data.remote.model.mypage.MyPageUserInfo
import com.example.data.repository.model.ResponseRepositoryModel
import com.example.domain.model.signup.UserAuth

internal interface MyPageRemoteDataSource {
    suspend fun getUserInfo(
        userAuth: UserAuth
    ): Result<ResponseRepositoryModel<MyPageUserInfo>>

    suspend fun getPortfoliosKeyword(
        accessToken: String,
        userId: Int,
    ): Result<ResponseRepositoryModel<String>>

    suspend fun getPortfolioExist(
        accessToken: String,
        userId: Int,
    ): Result<ResponseRepositoryModel<IsExist>>
}