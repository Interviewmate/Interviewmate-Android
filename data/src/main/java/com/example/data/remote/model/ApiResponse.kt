package com.example.data.remote.model

import com.example.data.repository.model.ResponseRepositoryModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ApiResponse<T>(
    val status: String,
    val message: String,
    val result: T
){
    fun toRepositoryModel() = ResponseRepositoryModel(
        status = status,
        message = message,
        result = result
    )
}