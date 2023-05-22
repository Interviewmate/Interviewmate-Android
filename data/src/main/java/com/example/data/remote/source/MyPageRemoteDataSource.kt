package com.example.data.remote.source

import com.example.data.remote.model.mypage.MyPageUserInfo
import com.example.data.repository.model.ResponseRepositoryModel

internal interface MyPageRemoteDataSource {
    suspend fun getUserInfo(
        userId: Int
    ): Result<ResponseRepositoryModel<MyPageUserInfo>>
}