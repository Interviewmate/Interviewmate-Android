package com.example.data.remote.source

import com.example.data.remote.model.mypage.MyPageUserInfo
import com.example.data.remote.network.MyPageApiService
import com.example.data.repository.model.ResponseRepositoryModel
import javax.inject.Inject

internal class MyPageRemoteDataSourceImpl @Inject constructor(
    private val myPageApiService: MyPageApiService
): MyPageRemoteDataSource {
    override suspend fun getUserInfo(userId: Int): Result<ResponseRepositoryModel<MyPageUserInfo>> {
        val response = myPageApiService.getUserInfo(userId)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }
}