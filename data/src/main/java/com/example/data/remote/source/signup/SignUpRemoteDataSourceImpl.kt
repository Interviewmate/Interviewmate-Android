package com.example.data.remote.source.signup

import com.example.data.remote.model.signup.*
import com.example.data.remote.model.signup.LoginUserInfo
import com.example.data.remote.model.signup.SignUpUserInfo
import com.example.data.remote.model.signup.UserAuth
import com.example.data.remote.model.signup.UserKeyword
import com.example.data.remote.network.signup.SignUpApiService
import com.example.data.repository.model.ResponseRepositoryModel
import javax.inject.Inject

internal class SignUpRemoteDataSourceImpl @Inject constructor(
    private val signUpApiService: SignUpApiService
) : SignUpRemoteDataSource {
    override suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Result<ResponseRepositoryModel<UserInfo>> {
        val response = signUpApiService.setSignUp(signUpUserInfo)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun sendEmail(email: String): Result<ResponseRepositoryModel<String>> {
        val response = signUpApiService.sendEmail(email)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun authenticateCode(
        email: String,
        code: String
    ): Result<ResponseRepositoryModel<String>> {
        val response = signUpApiService.authenticateCode(email, code)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun setLogin(
        userLoginInfo: LoginUserInfo
    ): Result<ResponseRepositoryModel<UserAuth>> {
        val response = signUpApiService.setLogin(userLoginInfo)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun checkNicknameDuplication(nickname: String): Result<ResponseRepositoryModel<String>> {
        val response = signUpApiService.checkNicknameDuplication(nickname)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun setKeywords(userKeyword: UserKeyword): Result<ResponseRepositoryModel<String>> {
        val response = signUpApiService.setKeywords(userKeyword)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

}