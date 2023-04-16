package com.example.data.remote.source.signup

import com.example.data.repository.model.EmailResponseRepositoryModel

internal interface SignUpRemoteDataSource {
    suspend fun sendEmail(email: String): Result<EmailResponseRepositoryModel>

    suspend fun authenticateCode(email: String, code: String): Result<EmailResponseRepositoryModel>

}