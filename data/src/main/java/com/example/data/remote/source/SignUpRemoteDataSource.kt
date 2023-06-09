package com.example.data.remote.source

import com.example.data.remote.model.signup.*
import com.example.data.remote.model.signup.LoginUserInfo
import com.example.data.remote.model.signup.SignUpUserInfo
import com.example.data.remote.model.signup.UserAuth
import com.example.data.repository.model.ResponseRepositoryModel

internal interface SignUpRemoteDataSource {

    suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Result<ResponseRepositoryModel<UserInfo>>

    suspend fun sendEmail(email: String): Result<ResponseRepositoryModel<String>>

    suspend fun authenticateCode(
        email: String,
        code: String
    ): Result<ResponseRepositoryModel<String>>

    suspend fun setLogin(userLoginInfo: LoginUserInfo): Result<ResponseRepositoryModel<UserAuth>>

    suspend fun checkNicknameDuplication(nickname: String): Result<ResponseRepositoryModel<String>>

    suspend fun setKeywords(userKeyword: UserKeyword): Result<ResponseRepositoryModel<String>>

}