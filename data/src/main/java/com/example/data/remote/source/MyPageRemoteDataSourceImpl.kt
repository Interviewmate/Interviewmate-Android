package com.example.data.remote.source

import com.example.data.remote.mapper.InterviewMapper
import com.example.data.remote.model.mypage.IsExist
import com.example.data.remote.model.mypage.MyPageUserInfo
import com.example.data.remote.network.MyPageApiService
import com.example.data.repository.model.ResponseRepositoryModel
import com.example.domain.model.signup.UserAuth
import javax.inject.Inject

internal class MyPageRemoteDataSourceImpl @Inject constructor(
    private val myPageApiService: MyPageApiService
) : MyPageRemoteDataSource {
    override suspend fun getUserInfo(userAuth: UserAuth): Result<ResponseRepositoryModel<MyPageUserInfo>> {
        val response = myPageApiService.getUserInfo(
            InterviewMapper.mapperToBearerToken(userAuth.accessToken),
            userAuth.userId
        )
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun getPortfoliosKeyword(
        accessToken: String,
        userId: Int
    ): Result<ResponseRepositoryModel<String>> {
        val response = myPageApiService.getPortfolioKeyword(
            InterviewMapper.mapperToBearerToken(accessToken),
            userId
        )
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun getPortfolioExist(
        accessToken: String,
        userId: Int
    ): Result<ResponseRepositoryModel<IsExist>> {
        val response = myPageApiService.getPortfolioExist(
            InterviewMapper.mapperToBearerToken(accessToken),
            userId
        )
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw java.lang.Exception(response.errorBody()?.string())
        }
    }
}