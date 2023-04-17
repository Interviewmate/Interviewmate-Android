package com.example.domain.model

data class ResponseUseCaseModel<T>(
    val status: String,
    val code: String,
    val message: String,
    val result: T
)