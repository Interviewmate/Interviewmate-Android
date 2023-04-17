package com.example.domain.model

data class LoginResponse(
    val status: String,
    val code: String,
    val message: String,
    val result: UserAuth
)