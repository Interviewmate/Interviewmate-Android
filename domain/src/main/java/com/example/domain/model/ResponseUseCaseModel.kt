package com.example.domain.model

data class ResponseUseCaseModel<T>(
    val status: String,
    val message: String,
    val result: T
)