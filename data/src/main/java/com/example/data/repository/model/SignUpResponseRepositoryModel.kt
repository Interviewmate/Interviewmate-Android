package com.example.data.repository.model

import com.example.data.remote.model.signup.UserInfo
import com.example.domain.model.SignUpResponse

internal data class SignUpResponseRepositoryModel(
    val status: String,
    val code: String,
    val message: String,
    val result: UserInfo
) {
    fun toDomainModel() = SignUpResponse(
        status = status,
        code = code,
        message = message,
        result = result.toDomainModel()
    )
}