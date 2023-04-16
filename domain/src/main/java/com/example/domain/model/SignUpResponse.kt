package com.example.domain.model

data class SignUpResponse(
    val status: String,
    val code: String,
    val message: String,
    val result: UserInfo
)