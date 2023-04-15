package com.example.data.remote.model.signup

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class EmailResponse(
    val status: String,
    val code: String,
    val message: String,
    val result: String
)