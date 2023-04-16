package com.example.domain.model

data class SignUpUserInfo(
    val email: String,
    val password: String,
    val job: String,
    val keywords: List<String>
)