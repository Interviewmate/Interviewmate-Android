package com.example.domain.repository

import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Flow<ResponseUseCaseModel<UserInfo>>

    suspend fun sendEmail(email: String): Flow<ResponseUseCaseModel<String>>

    suspend fun authenticateCode(email: String, code: String): Flow<ResponseUseCaseModel<String>>

    suspend fun setLogin(loginUserInfo: LoginUserInfo): Flow<ResponseUseCaseModel<UserAuth>>

}