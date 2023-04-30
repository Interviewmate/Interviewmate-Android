package com.example.data.remote.model.signup

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class LoginUserInfo(
    val email: String,
    val password: String
)