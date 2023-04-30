package com.example.data.remote.model.signup

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SignUpUserInfo(
    val email: String,
    val password: String,
    @Json(name = "nickName") val nickname: String,
    val job: String
)