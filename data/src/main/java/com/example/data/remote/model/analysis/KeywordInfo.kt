package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class KeywordInfo(
    val name: String,
    val count: Int
) {
    fun toDomainModel() = com.example.domain.model.analysis.KeywordInfo(
        name = name,
        count = count
    )
}