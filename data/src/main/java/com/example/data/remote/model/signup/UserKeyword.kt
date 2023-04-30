package com.example.data.remote.model.signup

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UserKeyword(
    val userId: Int,
    val keywords: List<String>
) {
    fun toDomainModel() = UserKeyword(
        userId = userId,
        keywords = keywords
    )
}
