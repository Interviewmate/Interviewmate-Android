package com.example.data.remote.source.signup

import com.example.data.remote.model.signup.LoginUserInfo
import com.example.data.remote.model.signup.SignUpUserInfo
import com.example.data.remote.model.signup.UserAuth
import com.example.data.remote.model.signup.UserInfo
import com.example.data.remote.network.signup.SignUpApiService
import com.example.data.repository.model.ResponseRepositoryModel
import javax.inject.Inject

internal class SignUpRemoteDataSourceImpl @Inject constructor(
    private val signUpApiService: SignUpApiService
) : SignUpRemoteDataSource {
    override suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Result<ResponseRepositoryModel<UserInfo>> =
        signUpApiService.setSignUp(signUpUserInfo).map { signUpResponse ->
            signUpResponse.toRepositoryModel()
        }

    override suspend fun sendEmail(email: String): Result<ResponseRepositoryModel<String>> =
        signUpApiService.sendEmail(email).map { emailResponse ->
            emailResponse.toRepositoryModel()
        }

    override suspend fun authenticateCode(
        email: String,
        code: String
    ): Result<ResponseRepositoryModel<String>> =
        signUpApiService.authenticateCode(email, code).map { emailResponse ->
            emailResponse.toRepositoryModel()
        }

    override suspend fun setLogin(
        userLoginInfo: LoginUserInfo
    ): Result<ResponseRepositoryModel<UserAuth>> =
        signUpApiService.setLogin(userLoginInfo).map { loginResponse ->
            loginResponse.toRepositoryModel()
        }

}