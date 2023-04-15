package com.example.domain.model

data class EmailResponse(
    val status: String,
    val code: String,
    val message: String,
    val result: String
)