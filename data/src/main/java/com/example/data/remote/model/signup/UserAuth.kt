package com.example.data.remote.model.signup

import com.example.domain.model.UserAuth
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UserAuth(
    val userId: Int,
    val accessToken: String
) {
    fun toDomainModel() = UserAuth(
        userId = userId,
        accessToken = accessToken
    )
}
