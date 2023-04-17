package com.example.data.remote.model.signup

import com.example.data.repository.model.LoginResponseRepositoryModel

internal data class LoginResponse(
    val status: String,
    val code: String,
    val message: String,
    val result: UserAuth
) {
    fun toRepositoryModel() = LoginResponseRepositoryModel(
        status = status,
        code = code,
        message = message,
        result = result
    )
}