package com.example.data.remote.source.signup

import com.example.data.remote.model.signup.LoginUserInfo
import com.example.data.remote.model.signup.SignUpUserInfo
import com.example.data.repository.model.EmailResponseRepositoryModel
import com.example.data.repository.model.LoginResponseRepositoryModel
import com.example.data.repository.model.SignUpResponseRepositoryModel

internal interface SignUpRemoteDataSource {

    suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Result<SignUpResponseRepositoryModel>

    suspend fun sendEmail(email: String): Result<EmailResponseRepositoryModel>

    suspend fun authenticateCode(email: String, code: String): Result<EmailResponseRepositoryModel>

    suspend fun setLogin(userLoginInfo: LoginUserInfo): Result<LoginResponseRepositoryModel>

}