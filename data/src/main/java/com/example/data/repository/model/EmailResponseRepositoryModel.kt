package com.example.data.repository.model

import com.example.domain.model.EmailResponse

internal data class EmailResponseRepositoryModel(
    val status: String,
    val code: String,
    val message: String,
    val result: String
) {
    fun toDomainModel() = EmailResponse(
        status = status,
        code = code,
        message = message,
        result = result
    )
}