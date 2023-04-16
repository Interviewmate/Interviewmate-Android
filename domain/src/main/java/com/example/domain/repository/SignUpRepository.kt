package com.example.domain.repository

import com.example.domain.model.EmailResponse
import com.example.domain.model.SignUpResponse
import com.example.domain.model.SignUpUserInfo
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Flow<SignUpResponse>

    suspend fun sendEmail(email: String): Flow<EmailResponse>

    suspend fun authenticateCode(email: String, code: String): Flow<EmailResponse>

}