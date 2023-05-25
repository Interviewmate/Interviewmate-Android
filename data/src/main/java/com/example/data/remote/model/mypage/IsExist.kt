package com.example.data.remote.model.mypage

import com.squareup.moshi.JsonClass
import com.example.domain.model.mypage.IsExist

@JsonClass(generateAdapter = true)
internal data class IsExist(
    val isExist: Boolean
) {
    fun toDomainModel() = IsExist(
        isExist = isExist
    )
}