package com.example.data.remote.model.signup

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SignUpUserInfo(
    val email: String,
    val password: String,
    val job: String,
    val keywords: List<String>
)