package com.example.data.remote.model.signup

import com.example.domain.model.signup.UserInfo
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo(
    val userId: Int,
    val email: String,
    val password: String
) {
    fun toDomainModel() = UserInfo(
        userId = userId,
        email = email,
        password = password
    )
}
