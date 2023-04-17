package com.example.data.repository.model

import com.example.data.remote.model.signup.UserAuth
import com.example.domain.model.LoginResponse

internal data class LoginResponseRepositoryModel(
    val status: String,
    val code: String,
    val message: String,
    val result: UserAuth
) {
    fun toDomainModel() = LoginResponse(
        status = status,
        code = code,
        message = message,
        result = result.toDomainModel()
    )
}