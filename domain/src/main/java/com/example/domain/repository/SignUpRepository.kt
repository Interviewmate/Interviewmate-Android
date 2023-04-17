package com.example.domain.repository

import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Flow<SignUpResponse>

    suspend fun sendEmail(email: String): Flow<EmailResponse>

    suspend fun authenticateCode(email: String, code: String): Flow<EmailResponse>

    suspend fun setLogin(loginUserInfo: LoginUserInfo): Flow<LoginResponse>

}