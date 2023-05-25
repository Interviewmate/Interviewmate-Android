package com.example.domain.repository

import com.example.domain.model.*
import com.example.domain.model.signup.*
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Flow<ResponseUseCaseModel<UserInfo>>

    suspend fun sendEmail(email: String): Flow<ResponseUseCaseModel<String>>

    suspend fun authenticateCode(email: String, code: String): Flow<ResponseUseCaseModel<String>>

    suspend fun setLogin(loginUserInfo: LoginUserInfo): Flow<ResponseUseCaseModel<UserAuth>>

    suspend fun checkNicknameDuplication(nickname: String): Flow<ResponseUseCaseModel<String>>

    suspend fun setKeywords(userKeyword: UserKeyword): Flow<ResponseUseCaseModel<String>>

}