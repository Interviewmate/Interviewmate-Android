package com.example.data.repository.model

import com.example.domain.model.ResponseUseCaseModel

internal data class ResponseRepositoryModel<T>(
    val status: String,
    val message: String,
    val result: T
) {
    fun <TT> toDomainModel(result: TT) = ResponseUseCaseModel(
        status = status,
        message = message,
        result = result
    )
}