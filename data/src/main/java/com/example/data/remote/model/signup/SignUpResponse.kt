package com.example.data.remote.model.signup

import com.example.data.repository.model.SignUpResponseRepositoryModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SignUpResponse(
    val status: String,
    val code: String,
    val message: String,
    val result: UserInfo
) {
    fun toRepositoryModel() = SignUpResponseRepositoryModel(
        status = status,
        code = code,
        message = message,
        result = result
    )
}