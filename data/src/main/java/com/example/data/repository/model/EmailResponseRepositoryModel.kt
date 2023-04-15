package com.example.data.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class EmailResponseRepositoryModel(
    val status: String,
    val code: String,
    val message: String,
    val result: String
)