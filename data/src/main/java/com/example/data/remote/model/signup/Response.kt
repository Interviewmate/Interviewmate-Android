package com.example.data.remote.model.signup

import com.example.data.repository.model.ResponseRepositoryModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Response<T>(
    val status: String,
    val code: String,
    val message: String,
    val result: T
){
    fun toRepositoryModel() = ResponseRepositoryModel(
        status = status,
        code = code,
        message = message,
        result = result
    )
}