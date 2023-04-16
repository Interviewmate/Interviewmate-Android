package com.example.data.remote.source.signup

import com.example.data.remote.model.signup.SignUpUserInfo
import com.example.data.remote.network.signup.SignUpApiService
import com.example.data.repository.model.EmailResponseRepositoryModel
import com.example.data.repository.model.SignUpResponseRepositoryModel
import javax.inject.Inject

internal class SignUpRemoteDataSourceImpl @Inject constructor(
    private val signUpApiService: SignUpApiService
) : SignUpRemoteDataSource {
    override suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Result<SignUpResponseRepositoryModel> =
        signUpApiService.setSignUp(signUpUserInfo).map { signUpResponse ->
            signUpResponse.toRepositoryModel()
        }

    override suspend fun sendEmail(email: String): Result<EmailResponseRepositoryModel> =
        signUpApiService.sendEmail(email).map { emailResponse ->
            emailResponse.toRepositoryModel()
        }

    override suspend fun authenticateCode(
        email: String,
        code: String
    ): Result<EmailResponseRepositoryModel> =
        signUpApiService.authenticateCode(email, code).map { emailResponse ->
            emailResponse.toRepositoryModel()
        }

}